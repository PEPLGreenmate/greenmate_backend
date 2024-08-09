package ai.greenmate.greenmate_backend.domain.report.service;

import ai.greenmate.greenmate_backend.domain.emotionmarble.entity.EmotionMarble;
import ai.greenmate.greenmate_backend.domain.emotionmarble.entity.EmotionMarbleKeyword;
import ai.greenmate.greenmate_backend.domain.emotionmarble.entity.EmotionType;
import ai.greenmate.greenmate_backend.domain.emotionmarble.entity.Keyword;
import ai.greenmate.greenmate_backend.domain.emotionmarble.repository.EmotionMarbleKeywordRepository;
import ai.greenmate.greenmate_backend.domain.emotionmarble.repository.EmotionMarbleRepository;
import ai.greenmate.greenmate_backend.domain.member.entity.Member;
import ai.greenmate.greenmate_backend.domain.member.repository.MemberRepository;
import ai.greenmate.greenmate_backend.domain.report.dto.EmotionMarbleDetailDTO;
import ai.greenmate.greenmate_backend.domain.report.dto.GetEmotionMarbleDetailResponse;
import ai.greenmate.greenmate_backend.domain.report.dto.GetReportResponse;
import ai.greenmate.greenmate_backend.domain.report.dto.GetReportsMonthResponse;
import ai.greenmate.greenmate_backend.global.dto.BaseResponseStatus;
import ai.greenmate.greenmate_backend.global.entity.BaseEntity;
import ai.greenmate.greenmate_backend.global.exception.GreenmateException;
import ai.greenmate.greenmate_backend.global.jwt.JwtService;
import ai.greenmate.greenmate_backend.global.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
  private final JwtService jwtService;
  private final EmotionMarbleRepository emotionMarbleRepository;
  private final EmotionMarbleKeywordRepository emotionMarbleKeywordRepository;
  private final MemberRepository memberRepository;

  public GetReportsMonthResponse getAvailableMonth() {
    String email = jwtService.getEmail();
    Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new GreenmateException(BaseResponseStatus.NOT_VALID_EMAIL));
    List<String> yearMonthList = emotionMarbleRepository.findByMember(member)
            .stream()
            .map(BaseEntity::getCreatedAt)
            .map(localDateTime -> {
              return DateUtils.getDateFromLocalDateTime(localDateTime, "yyyy-MM");
            })
            .distinct()
            .sorted()
            .toList();

    return new GetReportsMonthResponse(yearMonthList);
  }

  public GetReportResponse findReports(String date) {
    String email = jwtService.getEmail();
    Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new GreenmateException(BaseResponseStatus.NOT_VALID_EMAIL));

    LocalDate localDate = DateUtils.getLocalDateFromYearMonth(date, "yyyyMM");
    LocalDateTime startDate = DateUtils.getStartOfMonthFromLocalDate(localDate);
    LocalDateTime endDate = DateUtils.getEndOfMonthFromLocalDate(localDate);

    Map<EmotionType, Long> emotionCountMap = emotionMarbleRepository.findByMemberAndCreatedAtBetween(member, startDate, endDate)
            .stream()
            .map(EmotionMarble::getEmotionType)
            .collect(Collectors.groupingBy(emotionType -> emotionType, Collectors.counting()));
    return GetReportResponse.from(emotionCountMap, member);
  }

  public GetEmotionMarbleDetailResponse findEmotionMarbleDetail(String date) {
    String email = jwtService.getEmail();
    Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new GreenmateException(BaseResponseStatus.NOT_VALID_EMAIL));

    LocalDate localDate = DateUtils.getLocalDateFromYearMonth(date, "yyyyMM");
    LocalDateTime startDate = DateUtils.getStartOfMonthFromLocalDate(localDate);
    LocalDateTime endDate = DateUtils.getEndOfMonthFromLocalDate(localDate);


    List<EmotionMarbleKeyword> emotionMarbleKeywords = emotionMarbleKeywordRepository.findByMonthAndEmotionMarblesInFetchJoin(member, startDate, endDate);
    Map<String, List<EmotionMarbleDetailDTO>> result = createNewMap();

    Map<EmotionMarble, List<Keyword>> emotionMarbleKeywordMap = emotionMarbleKeywords.stream()
            .collect(Collectors.groupingBy(
                    EmotionMarbleKeyword::getEmotionMarble,  // 키로 사용될 EmotionMarble
                    Collectors.mapping(
                            EmotionMarbleKeyword::getKeyword,    // 값으로 사용될 Keyword
                            Collectors.toList()                  // 키워드들을 리스트로 수집
                    )
            ));
    System.out.println(emotionMarbleKeywordMap);
    emotionMarbleKeywordMap.forEach(
            (emotionMarble, keywordList) -> {
              result.get(emotionMarble.getEmotionToLowerCase()).add(EmotionMarbleDetailDTO.of(emotionMarble, keywordList));
            }
    );
    return new GetEmotionMarbleDetailResponse(result);
  }
  private Map<String, List<EmotionMarbleDetailDTO>> createNewMap() {
    Map<String, List<EmotionMarbleDetailDTO>> result = new HashMap<>();
    result.put(EmotionType.JOY.name().toLowerCase(), new ArrayList<>());
    result.put(EmotionType.ANGER.name().toLowerCase(), new ArrayList<>());
    result.put(EmotionType.ANXIETY.name().toLowerCase(), new ArrayList<>());
    result.put(EmotionType.HURT.name().toLowerCase(), new ArrayList<>());
    result.put(EmotionType.EMBARRASSMENT.name().toLowerCase(), new ArrayList<>());
    result.put(EmotionType.SADNESS.name().toLowerCase(), new ArrayList<>());
    return result;
  }
}

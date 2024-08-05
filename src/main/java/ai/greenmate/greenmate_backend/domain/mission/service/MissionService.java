package ai.greenmate.greenmate_backend.domain.mission.service;

import ai.greenmate.greenmate_backend.domain.greenmate.entity.Greenmate;
import ai.greenmate.greenmate_backend.domain.greenmate.repository.GreenmateRepository;
import ai.greenmate.greenmate_backend.domain.member.entity.Member;
import ai.greenmate.greenmate_backend.domain.member.repository.MemberRepository;
import ai.greenmate.greenmate_backend.domain.mission.dto.GetMissionDetailResponse;
import ai.greenmate.greenmate_backend.domain.mission.dto.GetMissionReviewResponse;
import ai.greenmate.greenmate_backend.domain.mission.dto.GetMissionsResponse;
import ai.greenmate.greenmate_backend.domain.mission.dto.MissionDTO;
import ai.greenmate.greenmate_backend.domain.mission.dto.PostReviewRequest;
import ai.greenmate.greenmate_backend.domain.mission.entity.Mission;
import ai.greenmate.greenmate_backend.domain.mission.entity.MissionStatus;
import ai.greenmate.greenmate_backend.domain.mission.repository.MissionRepository;
import ai.greenmate.greenmate_backend.global.dto.BaseResponseStatus;
import ai.greenmate.greenmate_backend.global.exception.GreenmateException;
import ai.greenmate.greenmate_backend.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionService {
  private final MemberRepository memberRepository;
  private final GreenmateRepository greenmateRepository;
  private final MissionRepository missionRepository;
  private final JwtService jwtService;

  public GetMissionsResponse findMissions(MissionStatus missionStatus) {
    if (missionStatus == null) {
      throw new GreenmateException(BaseResponseStatus.NOT_FOUND);
    }
    String email = jwtService.getEmail();
    Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new GreenmateException(BaseResponseStatus.NOT_VALID_EMAIL));
    List<Greenmate> greenmates = greenmateRepository.findByMemberWithGreenmateInfoFetchJoin(member);
    List<MissionDTO> missionDTOs = missionRepository.findByGreenmateIn(greenmates)
            .stream()
            .filter(mission -> mission.getMissionStatus().equals(missionStatus))
            .map(MissionDTO::fromMissionAndGreenmate)
            .toList();
    return new GetMissionsResponse(missionDTOs);
  }

  public GetMissionDetailResponse findMissionDetail(long missionId) {
    Mission mission = missionRepository.findById(missionId)
            .orElseThrow(() -> new GreenmateException(BaseResponseStatus.NOT_FOUND));
    return GetMissionDetailResponse.from(mission);
  }

  @Transactional
  public void acceptMission(long missionId) {
    Mission mission = missionRepository.findById(missionId)
            .orElseThrow(() -> new GreenmateException(BaseResponseStatus.NOT_FOUND));
    mission.updateMissionStatus(MissionStatus.IN_PROGRESS);
  }

  @Transactional
  public void completeMission(long missionId) {
    Mission mission = missionRepository.findById(missionId)
            .orElseThrow(() -> new GreenmateException(BaseResponseStatus.NOT_FOUND));
    mission.updateMissionStatus(MissionStatus.COMPLETED);
  }

  @Transactional
  public void cancelMission(long missionId) {
    Mission mission = missionRepository.findById(missionId)
            .orElseThrow(() -> new GreenmateException(BaseResponseStatus.NOT_FOUND));
    mission.updateMissionStatus(MissionStatus.NOT_ACCEPTED);
  }

  @Transactional
  public void reviewMission(long missionId, PostReviewRequest postReviewRequest) {
    log.info("postReviewRequest : " + postReviewRequest.toString());
    Mission mission = missionRepository.findById(missionId)
            .orElseThrow(() -> new GreenmateException(BaseResponseStatus.NOT_FOUND));
    mission.updateReview(postReviewRequest.getEmotion(), postReviewRequest.getContent());
  }

  public GetMissionReviewResponse findMissionReview(long missionId) {
    Mission mission = missionRepository.findById(missionId)
            .orElseThrow(() -> new GreenmateException(BaseResponseStatus.NOT_FOUND));
    return GetMissionReviewResponse.builder()
            .emotion(mission.getReviewEmotion().name())
            .content(mission.getReviewContent())
            .build();
  }
}

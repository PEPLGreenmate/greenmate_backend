package ai.greenmate.greenmate_backend.domain.greenmate.service;

import ai.greenmate.greenmate_backend.domain.greenmate.dto.GetGreenmatesResponse;
import ai.greenmate.greenmate_backend.domain.greenmate.dto.GreenmateInfoDTO;
import ai.greenmate.greenmate_backend.domain.greenmate.repository.GreenmateInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GreenmateInfoService {

  private final GreenmateInfoRepository greenmateInfoRepository;

  public GetGreenmatesResponse findAllGreenmates() {
    return new GetGreenmatesResponse(greenmateInfoRepository.findAll()
            .stream()
            .map(GreenmateInfoDTO::of)
            .toList());
  }
}

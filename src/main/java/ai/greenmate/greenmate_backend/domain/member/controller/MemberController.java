package ai.greenmate.greenmate_backend.domain.member.controller;

import ai.greenmate.greenmate_backend.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/")
@RestController
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;

}

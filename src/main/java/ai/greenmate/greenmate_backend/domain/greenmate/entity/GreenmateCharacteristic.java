package ai.greenmate.greenmate_backend.domain.greenmate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GreenmateCharacteristic {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "greenmate_id")
  private Greenmate greenmate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "characteristic_id")
  private Characteristic characteristic;
}

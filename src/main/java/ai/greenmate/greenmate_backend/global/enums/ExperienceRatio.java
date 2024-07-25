package ai.greenmate.greenmate_backend.global.enums;

public enum ExperienceRatio {
  ONCE(10,0,200),
  TWICE(ONCE.experience * 2, 200,300),
  THREE_TIMES(ONCE.experience * 3, 300, Integer.MAX_VALUE),;

  private final int experience;
  private final int minEnergy;
  private final int maxEnergy;

  ExperienceRatio(int experience, int minEnergy, int maxEnergy) {
    this.experience = experience;
    this.minEnergy = minEnergy;
    this.maxEnergy = maxEnergy;
  }

  public static int getExperienceByEnergy(int energy) {
    for (ExperienceRatio experienceRatio : ExperienceRatio.values()) {
      System.out.println(experienceRatio + " " + energy);
      int min = experienceRatio.minEnergy;
      int max = experienceRatio.maxEnergy;
      if(min <= energy && energy < max) {
        return experienceRatio.experience;
      }
    }
    return ONCE.experience;
  }
}

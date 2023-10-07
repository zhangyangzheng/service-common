package com.yz.work.common.app.sampling;

import java.util.Arrays;

/**
 * @author yangzhengzhang
 * @description
 * @date 2021-09-07 17:16
 */
public enum EnumMessageConvertDTORoute {

  CSP_RAPID_SETTLEMENT_FOLLOW_JOB_GEN(
      String.class, Integer.class);
  private Class<?> targetClazz;

  private Class<?> messageDTOClazz;

  EnumMessageConvertDTORoute(Class<?> targetClazz, Class<?> messageDTOClazz) {
    this.targetClazz = targetClazz;
    this.messageDTOClazz = messageDTOClazz;
  }

  public Class<?> getTargetClazz() {
    return targetClazz;
  }

  public void setTargetClazz(Class<?> targetClazz) {
    this.targetClazz = targetClazz;
  }

  public Class<?> getMessageDTOClazz() {
    return messageDTOClazz;
  }

  public void setMessageDTOClazz(Class<?> messageDTOClazz) {
    this.messageDTOClazz = messageDTOClazz;
  }

  public static EnumMessageConvertDTORoute getMessageConvertDTO(Class<?> targetClazz) {
    return Arrays.stream(EnumMessageConvertDTORoute.values())
        .filter(e -> e.getTargetClazz() == targetClazz)
        .findFirst()
        .get();
  }
}

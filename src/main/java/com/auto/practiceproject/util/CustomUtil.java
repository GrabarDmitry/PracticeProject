package com.auto.practiceproject.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CustomUtil {

  public String generatePassword(int len) {
    String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    Random rnd = new Random();

    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      sb.append(AB.charAt(rnd.nextInt(AB.length())));
    }
    return sb.toString();
  }
}

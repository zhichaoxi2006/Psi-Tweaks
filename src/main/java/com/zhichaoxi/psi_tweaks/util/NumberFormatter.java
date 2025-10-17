package com.zhichaoxi.psi_tweaks.util;

public class NumberFormatter {

    public static String formatNumber(long number) {
        if (number < 1000) {
            return String.valueOf(number);
        }

        String[] units = {"", "k", "M", "B", "T"};
        int unitIndex = 0;
        double num = number;

        while (num >= 1000.0 && unitIndex < units.length - 1) {
            num /= 1000.0;
            unitIndex++;
        }

        if (num == (long) num) {
            return String.format("%d%s", (long) num, units[unitIndex]);
        } else {
            return String.format("%.1f%s", num, units[unitIndex]);
        }
    }

    public static String formatNumber(double number) {
        if (Math.abs(number) < 1000) {
            return String.format("%.0f", number);
        }

        String[] units = {"", "k", "M", "B", "T"};
        int unitIndex = 0;
        double num = number;

        while (Math.abs(num) >= 1000.0 && unitIndex < units.length - 1) {
            num /= 1000.0;
            unitIndex++;
        }

        if (Math.abs(num) < 10) {
            return String.format("%.2f%s", num, units[unitIndex]);
        } else if (Math.abs(num) < 100) {
            return String.format("%.1f%s", num, units[unitIndex]);
        } else {
            return String.format("%.0f%s", num, units[unitIndex]);
        }
    }
}
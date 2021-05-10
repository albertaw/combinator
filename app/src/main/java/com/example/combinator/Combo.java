package com.example.combinator;

import java.util.ArrayList;
import java.util.List;

public class Combo {
    private List<Integer> digits;

    public Combo() {
        digits = new ArrayList<>();
    }

    public void addDigit(int digit) {
        digits.add(digit);
    }

    public List<Integer> getCombo() {
        return digits;
    }

    public void setCombo(List<Integer> digits) {
        this.digits = digits;
    }

    public int get(int index) {
        return digits.get(index);
    }

    public boolean equals(Combo combo) {
        for (int i = 0; i < 4; i++) {
            if (digits.get(i) != combo.get(i)) {
                return false;
            }
        }
        return true;
    }

    public int compareTo(Combo combo) {
        int result = -1;

        for (int i = 0; i < 4; i++) {
            //correct position
            if (digits.get(i) == combo.getCombo().get(i)) {
                return 0;
                //correct number
            } else if (digits.contains(combo.getCombo().get(i))) {
                result = 1;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String s = "";
        for (int digit : digits) {
            s += digit;
        }
        return s;
    }
}

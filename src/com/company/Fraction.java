package com.company;

public class Fraction {
    private int numerator, denominator, mixedNumber;

    /**
     * @param numerator
     * @param denominator must not be {@code null}
     * @param mixedNumber
     */
    public Fraction(int numerator, int denominator, int mixedNumber) {
        this.numerator = numerator;
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator must not be null!");
        }
        this.denominator = denominator;
        this.mixedNumber = mixedNumber;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public int getMixedNumber() {
        return mixedNumber;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public void setMixedNumber(int mixedNumber) {
        this.mixedNumber = mixedNumber;
    }

    public String toString() {
        if (mixedNumber == 0) {
            return "$\\dfrac{" + numerator + "}{" + denominator + "}$.\\";
        } else {
            return "{\\Huge " + mixedNumber + "}$\\dfrac{" + numerator + "}{" + denominator + "}$.\\\\";
        }
    }

    public Fraction multiplyWith(Fraction fraction) {
        int newNumerator, newDenominator;
        newNumerator = (this.mixedNumber*this.denominator + this.numerator) * (fraction.getMixedNumber()*fraction.getDenominator() + fraction.getNumerator());
        newDenominator = this.denominator * fraction.getDenominator();
        Fraction f = generateMixedNumber(newNumerator, newDenominator);
        f.kuerzen();
        return f;
    }

    public Fraction divideBy(Fraction fraction) {
        return multiplyWith(new Fraction(fraction.getDenominator(), fraction.getDenominator() * fraction.getMixedNumber() + fraction.getNumerator(), 0));
    }

    public Fraction sumWith(Fraction fraction) {
        if (this.denominator != fraction.getDenominator()) {
            this.gleichnamigMachen(fraction);
        }
        Fraction f = new Fraction(this.numerator + fraction.getNumerator(), this.denominator, this.mixedNumber + fraction.getMixedNumber());
        f.kuerzen();
        return f;
    }

    public Fraction subtract(Fraction fraction) {
        if (this.denominator != fraction.getDenominator()) {
            this.gleichnamigMachen(fraction);
        }
        Fraction f = new Fraction(this.numerator - fraction.getNumerator(), this.denominator, this.mixedNumber - fraction.getMixedNumber());
        f.kuerzen();
        return f;
    }

    public void gleichnamigMachen(Fraction fraction) {
        int i = this.denominator;
        this.numerator *= fraction.getDenominator();
        this.denominator *= fraction.getDenominator();
        fraction.setNumerator(fraction.getNumerator()*i);
        fraction.setDenominator(fraction.getDenominator()*i);
    }

    public Fraction generateMixedNumber(int newNumerator, int newDenominator) {
        int newMixedNumber;
        if (newNumerator >= newDenominator || newNumerator*-1 >= newDenominator) {
            newMixedNumber = (newNumerator-(newNumerator%newDenominator))/newDenominator;
        } else {
            newMixedNumber = 0;
        }
        newNumerator %= newDenominator;
        if (newMixedNumber < 0) {
            newNumerator *= -1;
        }
        return new Fraction(newNumerator, newDenominator, newMixedNumber);
    }

    public Fraction generateMixedNumber(Fraction fraction) {
        return generateMixedNumber(fraction.getNumerator() + fraction.getMixedNumber()*fraction.getDenominator(), fraction.getDenominator());
    }

    public void kuerzen () {
        int numer =  mixedNumber*denominator + numerator, denom = denominator;
        boolean numNeg = false, denNeg = false;
        if (numer < 0) {
            numNeg = true;
            numer *= -1;
        }
        if (denom < 0) {
            denNeg = true;
            denom *= -1;
        }
        int ggT = ggT(numer, denom);
        if (numNeg) {
            numer *= -1;
        }
        if (denNeg) {
            denom *= -1;
        }
        Fraction f = generateMixedNumber(numer / ggT, denom / ggT);
        this.numerator = f.getNumerator();
        this.denominator = f.getDenominator();
        this.mixedNumber = f.getMixedNumber();
    }

    private int ggT(int a, int b) {
        if (a < b) {
            return ggT(b, a);
        }
        int c = a % b;
        if (c==0) {
            return b;
        } else {
            return ggT(b, c);
        }
    }
}
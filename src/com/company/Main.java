package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        Fraction f = new Fraction(0, 1, 0);
        StringBuilder solution = new StringBuilder("\\documentclass{article}\n\\usepackage{amsmath}\n\\usepackage{amssymb}\n\\usepackage{longtable}\n\\setlength{\\parindent}{0pt}\n\\setlength{\\arrayrulewidth}{0.5mm}\n\\setlength{\\tabcolsep}{10pt}\n\\renewcommand{\\arraystretch}{2.5}\n\\begin{document}\n\\begin{center}\n\\begin{longtable}{||c | c | c | c | c | c | c | c | c||}\n\\hline\n\\hline\nNr. & Bruch a & Bruch b & a + b & a - b & b - a & a * b & a : b & b : a \\\\\n\\hline\\hline\n");
        StringBuilder task = new StringBuilder("\\documentclass{article}\n\\usepackage{amsmath}\n\\usepackage{amssymb}\n\\usepackage{longtable}\n\\setlength{\\parindent}{0pt}\n\\setlength{\\arrayrulewidth}{0.5mm}\n\\setlength{\\tabcolsep}{12pt}\n\\renewcommand{\\arraystretch}{2.5}\n\\begin{document}\n\\begin{center}\n\\begin{longtable}{||c | c | c | c | c | c | c | c | c||}\n\\hline\n\\hline\nNr. & Bruch a & Bruch b & a + b & a - b & b - a & a * b & a : b & b : a \\\\\n\\hline\\hline\n");
        for (int i = 0; i < 99; i++) {
            Fraction a = f.generateMixedNumber((ThreadLocalRandom.current().nextInt(1,21)), (ThreadLocalRandom.current().nextInt(1,21)));
            Fraction b = f.generateMixedNumber((ThreadLocalRandom.current().nextInt(1,21)), (ThreadLocalRandom.current().nextInt(1,21)));
            solution.append(i+1 + "&");
            task.append(i+1 + "&");

            solution.append(a.toString() + "&");
            solution.append(b.toString() + "&");
            task.append(a.toString() + "&");
            task.append(b.toString() + "&");

            solution.append(a.sumWith(b).toString() + "&");
            solution.append(a.subtract(b).toString() + "&");
            solution.append(b.subtract(a).toString() + "&");
            solution.append(a.multiplyWith(b).toString() + "&");
            solution.append(a.divideBy(b).toString() + "&");
            solution.append(b.divideBy(a).toString() + "&");
            task.append("           &           &           &           &           &           &");

            solution.append("\n\\hline\n");
            task.append("\n\\hline\n");
        }
        solution.append("\\end{longtable}\n\\end{center}\n\\end{document}");
        task.append("\\end{longtable}\n\\end{center}\n\\end{document}");
        System.out.println(solution);
        System.out.println("\n--Aufgabe--\n");
        System.out.println(task);
    }
}
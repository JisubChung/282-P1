package pa1;

public class Test1{

    public static void main(String[] args) {
        
        String s[][] = {
                {
                    "800 000 042",
                    "007 059 063",
                    "000 000 900",
                    
                    "000 900 400",
                    "650 080 071",
                    "004 003 000",
                    
                    "002 000 000",
                    "910 620 800",
                    "780 000 004"
                }
        };
        
        String answers[] = {
                "895316742427859163361247985138975426659482371274163598542738619913624857786591234"
            };
        String rotateAnswers[] = {
                "795261348814753629632498175567149283923687451148325796286534917351972864479816532"
            };
        sudoku p;
        
        System.out.println("Author: " + sudoku.myName());
        for (int i = 0; i < s.length; i++) {
            p = new sudoku(s[i]);
            //
            System.out.println("The final board: \n\n" + p);
            System.out.print("#" + i + ": ");
            p.solve();
            if (p.isComplete())
                System.out.print("Solution found.");
            else
                System.out.print("Not done yet.  ");
            if (p.toString2().compareTo(answers[i]) == 0) 
                System.out.print(" Answers match.   ");
            else
                System.out.print("   *** NO MATCH ***   ");
            System.out.println("The final board: \n\n" + p);
            p = new sudoku(s[i]);
            p.rotate();
            p.solve();
            if (p.isComplete())
                System.out.print("Solution found.");
            else
                System.out.print("Not done yet.  ");
            if (p.toString2().compareTo(rotateAnswers[i]) == 0) 
                System.out.print(" Answers match.    ");
            else
                System.out.print("   *** NO MATCH *** ");
            System.out.println("The final board: \n\n" + p);
            System.out.println();
        }
    }

}

/*  Output produced by my program 

Author: Alfred E. Newman
#0: Solution found. Answers match.   The final board: 

895 | 316 | 742
427 | 859 | 163
361 | 247 | 985
---------------
138 | 975 | 426
659 | 482 | 371
274 | 163 | 598
---------------
542 | 738 | 619
913 | 624 | 857
786 | 591 | 234

Solution found. Answers match.    The final board: 

795 | 261 | 348
814 | 753 | 629
632 | 498 | 175
---------------
567 | 149 | 283
923 | 687 | 451
148 | 325 | 796
---------------
286 | 534 | 917
351 | 972 | 864
479 | 816 | 532



*/

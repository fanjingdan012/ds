package problem;/*    */
/*    */ import java.io.PrintStream;
/*    */ import javax.swing.JOptionPane;
/*    */ 
/*    */ public class triangle
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/*  6 */     String numString = JOptionPane.showInputDialog("Enter the number of symbolin the first line");
/*  7 */     int num = Integer.parseInt(numString);
/*  8 */     int[] a = new int[num];
/*  9 */     int total = amount(num);
/* 10 */     int countnum = 0;
/* 11 */     System.out.println(maketriangle(a, num));
/*    */   }
/*    */   public static int calculate(int[] a, int n) {
/* 14 */     int sum = 0;
/* 15 */     if (n < 2) {
/* 16 */       return sum += a[0];
/*    */     }
/* 18 */     for (int i = 0; i < n; i++) {
/* 19 */       sum += a[i];
/* 20 */       if (i < n - 1)
/* 21 */         a[i] ^= a[(i + 1)];
/*    */     }
/* 23 */     sum += calculate(a, n - 1);
/* 24 */     return sum;
/*    */   }
/*    */   public static int getnumber(int[] a) {
/* 27 */     if (amount(a.length) == 2 * calculate(a, a.length))
/* 28 */       return 1;
/* 29 */     return 0;
/*    */   }
/*    */ 
/*    */   public static int maketriangle(int[] a, int n) {
/* 33 */     int result = 0;
/* 34 */     if (n == 0) {
/* 35 */       result += getnumber(a);
/* 36 */       return result;
/*    */     }
/* 38 */     for (int i = 0; i <= 1; i++) {
/* 39 */       a[(n - 1)] = i;
/* 40 */       result += maketriangle(a, n - 1);
/*    */     }
/* 42 */     return result;
/*    */   }
/*    */   public static int amount(int n) {
/* 45 */     return (1 + n) * n / 2;
/*    */   }
/*    */ }

/* Location:           /home/congliu/Downloads/
 * Qualified Name:     DS.problem.triangle
 * JD-Core Version:    0.6.0
 */
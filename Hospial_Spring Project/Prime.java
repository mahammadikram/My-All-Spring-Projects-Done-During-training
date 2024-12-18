import java.util.Scanner;

public class Prime {
    public static void main(String []args){
    int num;
    Scanner sc=new Scanner(System.in);
    num=sc.nextInt();
    int cnt=0;
    for(int i=1;i<num;i++){
    if(num%i==0){
    cnt++;
    }
    }
    if(cnt==2){
    System.out.println("prime Number");
    }
    else{
    System.out.println("Not A prime Number");
    }
    }
    }
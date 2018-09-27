import java.util.Arrays;
import java.util.ArrayList;  
import java.util.Collections;  
import java.util.Iterator;  
import java.util.List;  
public class Median_Finding
{   
	public static int SortFind(int s[],int k)
	{
		Arrays.sort(s);
		return s[k-1];
	}
	public static int Finding(int s[],int k)
	{   
		int length=s.length;
		if(length<=5)
			return SortFind(s,k);    //终止条件
		int p=(int)Math.ceil(length/5.0);
        int []medians=new int[p];
        for(int i=1;i<p;i++)
        {
             medians[i-1]=SortFind(Arrays.copyOfRange(s, (i-1)*5, (i-1)*5+5),3);
        }
        medians[p-1]=Finding(Arrays.copyOfRange(s,(p-1)*5, length),(length+1-(p-1)*5)/2);
        int median=Finding(medians,(p+1)/2);        //使用Finding函数递归的寻找中位数
        int less=0;
        int []temp=new int[length];
        int temp1=length-1;
        for(int i=0;i<length;i++)
        {
        	if(s[i]<median)
        		temp[less++]=s[i];
        	else
        		temp[temp1--]=s[i];
        		
        }
        if(less==k-1)
        	return median;
        
        if(less>k-1)
        	return Finding(Arrays.copyOfRange(temp, 0, less),k);
        else
        	return Finding(Arrays.copyOfRange(temp, less, length),k-less);
	}
	
	public static void main(String[] args)
	{
		int []d=new int[10000];
		List list=new ArrayList();
        for(int i=0;i<10000;i++)
        {
        	list.add(i);
        }
        Collections.shuffle(list);
        int i=0;
        Iterator ite=list.iterator();
        while(ite.hasNext())
        {
        	d[i++]=(int)ite.next();
        }
	    System.out.println(Finding(d,1000));
	}
}
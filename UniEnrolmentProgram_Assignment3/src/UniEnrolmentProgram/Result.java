/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniEnrolmentProgram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author linh
 */
enum Grade implements Serializable
{
    NOT_FOUND(-1),F(0),P(50),C(65),D(75),HD(85);
    int threshold;
    Grade(int threshold)
    {
        this.threshold = threshold;
    }
    public double getThreshold()
    {
        return threshold;
    }
}
public class Result implements Serializable, Cloneable, Comparable<Result>
{
    private double mark;
    public Result(double mark)
    {
        this.mark = mark;
    }
    public static ArrayList<Result> deepCopy(ArrayList<Result> results) throws CloneNotSupportedException 
    {
        ArrayList<Result> deepCopy = new ArrayList<>();
        for(Result result:results)
        {
            deepCopy.add(result.clone());
        }
        return deepCopy;
    }

    static HashMap<String, Result> deepCopy(HashMap<String, Result> results) throws CloneNotSupportedException 
    {
        HashMap<String,Result> deepCopy = new HashMap<>();
        for(String id:results.keySet())
        {
            deepCopy.put(id, results.get(id).clone());
        }
        return deepCopy;
    }
    
    public void setMark(double mark)
    {
        this.mark=mark;
    }
    public double getMark()
    {
        return mark;
    }
    public Grade getGrade()
    {
        if(mark>=Grade.HD.getThreshold())
        {
            return Grade.HD;
        } 
        if(mark>=Grade.D.getThreshold())
        {
            return Grade.D;
        }
        if(mark>=Grade.C.getThreshold())
        {
            return Grade.C;
        }
        if(mark>=Grade.P.getThreshold())
        {
            return Grade.P;
        }
        if(mark>=Grade.F.getThreshold())
        {
            return Grade.F;
        }
        return Grade.NOT_FOUND;
    }
    public String toString()
    {
        return mark+"("+getGrade()+")";
    }
    public void print()
    {
        System.out.format("%15s %15s",mark,getGrade());
    }
    public static Result getHighestMark(ArrayList<Result> results)
    {
        if(!results.isEmpty())
        {
            double max=results.get(0).getMark();
            for(Result result:results)
            {
                if(result.mark>max)
                {
                    max=result.mark;
                }
            }
            return new Result(max);
        } else
        {
            return new Result(-1);
        }
    }
    public static Result getLowestMark(ArrayList<Result> results)
    {
        if(results.isEmpty())
        {
            return new Result(-1);
        } 
        else
        {
            double min=Integer.MAX_VALUE;
            for(Result result:results)
            {
                if(result.mark!=-1&&result.mark<min)
                {
                    min=result.mark;
                }
            }
            if(min!=Integer.MAX_VALUE)
            {
                return new Result(min);
            }
            else
            {
                return new Result(-1);
            }
        }
    }
    public static Result getAverageMark(ArrayList<Result> results)
    {
        double sum=0;double count=0;
        for(Result result:results)
        {
            if(result.getMark()>-1)//check if mark is recorded
            {
                sum+=result.getMark();
                count++;
            }
        }
        if (count==0)
        {
            return new Result(-1);
        }
        return new Result(sum/count);
    }
    
    @Override
    public int compareTo(Result that) 
    {
        return Double.compare(mark, that.mark);
    }
    public Result clone() throws CloneNotSupportedException
    {
        return (Result) super.clone();
    }

    double toDelimitedString() 
    {
        return mark;
    }
}

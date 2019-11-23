import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class adjListConversion {

  public static class adjMapper
       extends Mapper<Object, Text, Text, Text>{


    private Text outVal = new Text();
      private Text outKey = new Text();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException
    {
        String inline = value.toString();
        if (!inline.startsWith("#"))
        {
            String [] inVals = inline.split("\t");
            outKey.set(inVals[0]);
            outVal.set(inVals[1]);
            context.write(outKey, outVal);
            outKey.set(inVals[1]);
            outVal.set(inVals[0]);
            context.write(outKey, outVal);
        }
    }
  }

  public static class adjReducer
      extends Reducer<Text,Text,Text, Text> {
    private Text result = new Text();

    public void reduce(Text key, Iterable<Text> values,
                       Context context
                       ) throws IOException, InterruptedException {

      for (Text val : values)
      {
          context.write(key ,val);
      }
    }
  }

  public static void main(String[] args) throws Exception
  {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "Directed to Undirected Conversion");
    job.setJarByClass(adjListConversion.class);
    job.setMapperClass(adjMapper.class);
    job.setReducerClass(adjReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}

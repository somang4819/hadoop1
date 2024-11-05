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

// WordCount 클래스 정의: Hadoop MapReduce 작업을 수행하여 텍스트 파일에서 단어의 발생 빈도를 계산
public class WordCount {

  // Mapper 클래스: 입력 데이터를 개별 단어로 분리하고 각 단어에 대해 <단어, 1> 형태의 키-값 쌍 생성
  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    // 각 단어의 발생을 1로 기록
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    // map 메소드: 입력값을 단어 단위로 분리하고, 각 단어에 대해 <단어, 1> 형태의 출력을 기록
    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());
      while (itr.hasMoreTokens()) {
        word.set(itr.nextToken());
        context.write(word, one); // 각 단어의 발생 횟수를 1로 설정하여 context에 출력
      }
    }
  }

  // Reducer 클래스: 동일한 키(단어)에 대해 값(1)의 합을 계산하여 최종 단어 발생 횟수를 출력
  public static class IntSumReducer
       extends Reducer<Text, IntWritable, Text, IntWritable> {
    private IntWritable result = new IntWritable();

    // reduce 메소드: 단어별로 발생 횟수를 합산하여 최종 결과를 context에 출력
    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable val : values) {
        sum += val.get(); // 단어 발생 횟수를 모두 합산
      }
      result.set(sum);
      context.write(key, result); // 단어와 총 발생 횟수를 context에 출력
    }
  }

  // main 메소드: Job 설정과 실행을 담당
  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration(); // Hadoop 설정 객체 생성
    
    Job job = Job.getInstance(conf, "word count"); // 새로운 Job 인스턴스 생성 및 작업 이름 설정
    job.setJarByClass(WordCount.class); // 해당 클래스를 Jar 파일의 메인 클래스 설정
    job.setMapperClass(TokenizerMapper.class); // Mapper 클래스를 TokenizerMapper로 설정
    job.setCombinerClass(IntSumReducer.class); // Combiner 클래스를 Reducer와 동일하게 설정
    job.setReducerClass(IntSumReducer.class); // Reducer 클래스를 IntSumReducer로 설정
    job.setOutputKeyClass(Text.class); // 출력 키 타입 설정 (단어)
    job.setOutputValueClass(IntWritable.class); // 출력 값 타입 설정 (단어 발생 횟수)
    
    FileInputFormat.addInputPath(job, new Path(args[0])); // 입력 파일 경로 설정
    FileOutputFormat.setOutputPath(job, new Path(args[1])); // 출력 파일 경로 설정
    
    System.exit(job.waitForCompletion(true) ? 0 : 1); // 작업 완료 상태에 따라 종료 코드 반환
  }
}

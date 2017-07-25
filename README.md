# AdCountDownViewDemo
# 欢迎页倒计时控件

![image](http://getimg.jrj.com.cn/images/2014/02/tmtpostimg/one_20140217152310659.jpg)

AndroidStudio使用Gradle构建添加依赖（推荐）

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
```java
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

Step 2. Add the dependency
```java
	dependencies {
	        compile 'com.github.GuoZhiHao9396:AdCountDownViewDemo:v1.0.1'
	}
```
Or Maven:

Step 1. Add the JitPack repository to your build file

gradle
maven
sbt
leiningen
```java
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```	

Step 2. Add the dependency
```java
	<dependency>
	    <groupId>com.github.GuoZhiHao9396</groupId>
	    <artifactId>AdCountDownViewDemo</artifactId>
	    <version>v1.0.1</version>
	</dependency>
```
# 使用介绍

1.xml属性设置
```java
gzh_canvasbase_color：底盘的颜色
gzh_progress_color：进度条的颜色
gzh_progress_size：进度条粗细
gzh_text_content：文字内容
gzh_text_color：文字颜色
gzh_text_size：文字大小
gzh_time_size：倒计时间
```
2.java代码设置
```java
public class MainActivity extends AppCompatActivity {
    CountDownView cdv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cdv = (CountDownView) findViewById(R.id.cdv);
        //开始倒计时
        cdv.start();
        cdv.setCountDownTimerListener(new CountDownView.CountDownTimerListener() {
            @Override
            public void onStartCount() {
            }

            @Override
            public void onFinishCount() {
                Toast.makeText(MainActivity.this, "加载完毕", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(MainActivity.this, SendActivity.class);
                startActivity(in);
                finish();
            }
        });
        cdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //结束倒计时
                cdv.stop();
                Intent in = new Intent(MainActivity.this, SendActivity.class);
                startActivity(in);
                finish();
            }
        });
    }
}
```

# License
```text
Copyright 2016 ZhiHao Guo

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

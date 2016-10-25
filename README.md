# AdCountDownViewDemo
欢迎页倒计时控件

![image](http://getimg.jrj.com.cn/images/2014/02/tmtpostimg/one_20140217152310659.jpg)

AndroidStudio使用Gradle构建添加依赖（推荐）

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.GuoZhiHao9396:AdCountDownViewDemo:v1.0.1'
	}
	
Or Maven:

Step 1. Add the JitPack repository to your build file

gradle
maven
sbt
leiningen
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
Step 2. Add the dependency

	<dependency>
	    <groupId>com.github.GuoZhiHao9396</groupId>
	    <artifactId>AdCountDownViewDemo</artifactId>
	    <version>v1.0.1</version>
	</dependency>


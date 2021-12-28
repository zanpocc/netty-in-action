
## Channel
channel是线程安全的，所以可以将channel对象给其它的线程持有，不会有线程安全的问题。




## 线程池
### EventLoop
EventLoop和EventLoopGroup拓展了Java的线程池类，通过线程池的方式进行事件循环
和事件的转发执行。

### SchedulerExecutor
Netty拓展了Java的调度线程池类，使用Netty可以实现相同的功能，并拥有更小的开销。


## 组件关系
 - EventLoopGroup
 - EventLoop
 - Channel
 - ChannelPipeline
 - Thread
 - Handler


1 、EventLoopGroup = 线程池
2、EventLoop = Thread(绑定，等于)
3、异步传输：一个EventLoop下有多个Channel，每个Channel生命周期中都属于这个EventLoop， 即多个Channel共享一个ThreadLocal。
阻塞传输：一个EventLoop对应一个Channel。
4、每个Channel都有一个pipeline，里面放着一个handler的列表，串着入站和出站消息的handler，按照添加顺序执行。




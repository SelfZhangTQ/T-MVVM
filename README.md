## T-MVVM 努力打造简单实用的开发框架

## 艺术帮是一款艺术图片应用，采用LiveData+ViewModel+RxJava+okHttp+Retrofit+Glide架构的项目,仅用于学习交流，不断完善中<br/>
   项目github地址:<https://github.com/SelfZhangTQ/T-MVVM> <br/>
#### 项目初衷 在此之前一直使用MVP,MVP缺点:<br/>
   * Presenter中除了逻辑以外，还有大量的View->Model，Model->View的逻辑操作，造成Presenter臃肿，维护困难。<br/>
   * 对UI的渲染放在了Presenter中，所以UI和Presenter的交互会过于频繁。<br/>
   * Presenter过多地渲染了UI，往往会使得它与特定的UI的交互频繁。一旦UI变动，Presenter也需要变<br/>
   * 接口暴增,可以说代码量成倍增长,让人无法忍受,<br/>
#### MVVM的出现提高可维护性。解决了MVP大量的手动View和Model同步的问题,但并非救世主。没有最好的框架，只有最适合自己的，
#### 此项目未使用:DataBinding原由<br/>
   * 数据绑定增加Bug调试难度。<br/>
   * 复杂的页面，model也会很大，虽然使用方便了也很容易保证了数据的一致性，当时长期持有，不利于释放内存。<br/>
   * 数据双向绑定不利于View重用。<br/>

## 无图无真相<br/>
![image](https://github.com/SelfZhangTQ/T-MVVM/raw/master/screenshots/1_video.gif)
![image](https://github.com/SelfZhangTQ/T-MVVM/raw/master/screenshots/2_video.gif)<br/>
![image](https://github.com/SelfZhangTQ/T-MVVM/raw/master/screenshots/3_video.gif)
![image](https://github.com/SelfZhangTQ/T-MVVM/raw/master/screenshots/4_video.gif)<br/>
![image](https://github.com/SelfZhangTQ/T-MVVM/raw/master/screenshots/5_video.gif)
![image](https://github.com/SelfZhangTQ/T-MVVM/raw/master/screenshots/6_video.gif)<br/>

## 第三方库 <br/>
* okHttp <br/>
* Retrofit <br/>
* RxJava <br/>
* LiveData <br/>
* ViewModel <br/>
* ButterKnife <br/>
* Glide <br/>
* Gson <br/>
* BottomNavigationBar(轻量级底部导航栏)<br/>
* [LoadState](https://github.com/SelfZhangTQ/LoadState) (统一管理错误页面库)<br/>
* gsyVideoPlayer(视频播放)<br/>
* [TRecyclerView](https://github.com/SelfZhangTQ/TRecyclerView) (面向ViewHolder开发的刷新库,多类型item终结者,好不好用你试试就知道) <br/>
* ... <br/>

## 声明 <br/>

这个属于个人开发作品，仅做学习交流使用。诸位勿传播于非技术人员，拒绝用于商业用途，数据均属于非正常渠道获取。

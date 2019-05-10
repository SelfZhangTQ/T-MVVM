## T-MVVM 努力打造简单实用的开发框架

## 艺术帮是一款艺术图片应用，采用LiveData+ViewModel+RxJava+okHttp+Retrofit+Glide架构的项目,仅用于学习交流，不断完善中<br/>
   项目github地址:<https://github.com/SelfZhangTQ/T-MVVM> <br/>
   [apk下载地址](https://github.com/SelfZhangTQ/T-MVVM/raw/master/screenshots/app.apk)






#### T-MVVM 2.0版本更新说明<br/>
     * 基于LiveBus事件除去ViewModel和Repository接口耦合。





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


#### 工程代码说明<br/>
![image](https://github.com/SelfZhangTQ/T-MVVM/raw/master/screenshots/mvvm3.png)


## 无图无真相<br/>
![image](https://github.com/SelfZhangTQ/T-MVVM/raw/master/screenshots/1_video.gif)
![image](https://github.com/SelfZhangTQ/T-MVVM/raw/master/screenshots/2_video.gif)<br/>
![image](https://github.com/SelfZhangTQ/T-MVVM/raw/master/screenshots/3_video.gif)
![image](https://github.com/SelfZhangTQ/T-MVVM/raw/master/screenshots/4_video.gif)<br/>
![image](https://github.com/SelfZhangTQ/T-MVVM/raw/master/screenshots/5_video.gif)
![image](https://github.com/SelfZhangTQ/T-MVVM/raw/master/screenshots/6_video.gif)<br/>

 基于ViewModel,LiveData,Retrofit,Rxjava实现T-MVVM体系结构的架构，泛型限定，深度解耦。

    ViewModel优点：
     * 同步关联生命周期，
     * 数据共享 ，
     * 复用性强 ，
    LiveData优点：
     * 确保UI界面的数据状态，
     * 没有内存泄漏,不会因为Activity的不可见导致Crash，
     * 不用再人为的处理生命周期，
     * 共享资源，

  此架构未使用DataBinding原由：

    * 数据绑定增加Bug调试难度，
    * 复杂的页面，model也会很大，虽然使用方便了也很容易保证了数据的一致性，当时长期持有，不利于释放内存，
    * 数据双向绑定不利于View重用，



###T-MVVM架构分层代码
  1：先定义BaseViewModel基类

     /**
      * @author：tqzhang on 18/7/26 16:15
      */
      public class BaseViewModel<T extends BaseRepository> extends AndroidViewModel {

        public T mRepository;

        public BaseViewModel(@NonNull Application application) {
            super(application);
            mRepository = TUtil.getNewInstance(this, 0);
        }

        @Override
        protected void onCleared() {
            super.onCleared();
            if (mRepository != null) {
               mRepository.unSubscribe();
            }

       }
     }

BaseViewModel通过泛型类型参数BaseRepository子类初始化Repository数据仓库，同时在activity/fragment走onDestroy()生命周期方法时 BaseViewModel回调onCleared，即页面销毁是用来取消网络请求或资源释放等操作。

正常开发一般不建议直接通过ViewModel获取网络数据，这里我们将工作交给一个新的模块Repository。Repository只负责数据处理，提供干净的api，方便切换数据来源。

2：再定义BaseRepository

    public abstract class BaseRepository {

         protected ApiService apiService;

         public BaseRepository() {
                 if (null == apiService) {
                     apiService = HttpHelper.getInstance().create(ApiService.class);
                 }
         }

         private CompositeSubscription mCompositeSubscription;

         protected void addSubscribe(Subscription subscription) {
                if (mCompositeSubscription == null) {
                    mCompositeSubscription = new CompositeSubscription();
                }
               mCompositeSubscription.add(subscription);
          }

         public void unSubscribe() {
                 if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
                mCompositeSubscription.clear();
        }
    }

   BaseRepository中内容相对简单，主要是获取ApiService和网络请求订阅容器，方便管理网络请求。

3：然后自定义AbsLifecycleFragment基类继承BaseFragment，BaseFragment可自行编写。如不需要使用T-MVVM，可自行继承BaseFragment，互不影响。


     public abstract class AbsLifecycleFragment<T extends BaseViewModel> extends BaseFragment  {

           protected T mViewModel;
           /**
            * init view
            * @param state
            */
           @Override
           public void initView(Bundle state) {
                mViewModel = VMProviders(this, TUtil.getInstance(this, 0));
              if (null != mViewModel) {
                dataObserver();
              }
           }

          /**
           * create ViewModelProviders
           *
           * @return ViewModel
           */
          protected <T extends ViewModel> T VMProviders(BaseFragment fragment, @NonNull Class<T> modelClass) {
               return ViewModelProviders.of(fragment).get(modelClass);
          }

          protected void dataObserver() {

          }

         /**
          * 获取网络数据
          */
          protected void getRemoteData() {

          }

        }


在initView方法中通过BaseViewModel子类泛型类型参数获取Class<T>，在通过ViewModelProviders.of(fragment).get(modelClass))实例化ViewModel,
到此我们的基类基本编写完毕。

4：下面我们以一个简单业务实战下，获取文章列表。

  4-1:ArticleFragment

    /**
    * @author：tqzhang on 18/7/2 14:40
    */
    public class ArticleFragment extends AbsLifecycleFragment<ArticleViewModel> {
     protected TRecyclerView mRecyclerView;
     protected StaggeredGridLayoutManager layoutManager;
     protected DelegateAdapter adapter;

     public static ArticleFragment newInstance() {
        return new ArticleFragment();
     }

     @Override
     public int getLayoutResId() {
        return R.layout.fragment_list;
     }

     @Override
     public void initView(Bundle state) {
        super.initView(state);
        layoutManager=new new StaggeredGridLayoutManager(1,  StaggeredGridLayoutManager.VERTICAL);
        //获取网络数据
        getRemoteData()；
     }

     //初始化adapter
     public void initAdapter(){
       adapter= new DelegateAdapter.Builder<>()
               .bindArray(ArticleInfoVo.class, new ArticleRem1ItemHolder(context)
                , new ArticleRem2ItemHolder(context)
                , new ArticleRem3ItemHolder(context))
               .bind(HeaderVo.class, new HeaderViewHolder(context, rogressStyle.Pacman))
               .bind(FootVo.class, new FootViewHolder(context, ProgressStyle.Pacman))
               .build();


     //数据观察
     @Override
     protected void dataObserver() {
        mViewModel.getArticleList().observe(this, articleVo -> {
            if (null != articleVo) {
               mRecyclerView.refreshComplete(articleVo.data.list, false);
            }
        });
     }
     //获取网络数据
     @Override
     protected void getRemoteData() {
         mViewModel.getArticleList(typeId, lastId);
     }
    }

 我们可以看出来ArticleFragment中只有UI初始化,发请网络请求action以及数据观察，列表展示用了[TRecyclerView,欢迎star哦](https://github.com/SelfZhangTQ/TRecyclerView)面向holder开发高复用，多类型的刷新库，从此只关心你的列表的Item展示。
通过泛型除去了MVP中通过接口传递信息的大量代码，
从此see you Mass implementation of interfaces。



 4-1:ArticleViewModel

    /**
    * @author：tqzhang on 18/7/26 16:15
    */
    public class ArticleViewModel extends BaseViewModel<ArticleRepository> {

         private MutableLiveData<ArticleVo> mArticleData;

         public ArticleViewModel(@NonNull Application application) {
              super(application);
         }

         public LiveData<ArticleVo> getArticleList() {
              if (mArticleData == null) {
                  mArticleData = new MutableLiveData<>();
              }
             return mArticleData;
         }

        public void getArticleList(String lectureLevel1, String lastId) {
              mRepository.loadArticleRemList(new CallBack<ArticleVo>() {
            @Override
            public void onNext(ArticleVo articleObject) {
                mArticleData.postValue(articleObject);
            }

            @Override
            public void onError(String e) {

            }            }
        });
    }

  ArticleViewModel中持有数据观察容器LiveData和真正发起网络请求动作，在接收到服务端返回的数据通过
 mArticleData.postValue(articleObject);通知Observer数据的更改，此处需注意的是，setValue方法只能在主线程中调用，postValue可以在任何线程中调用，如果是在后台子线程中更新LiveData的值，必须调用postValue。


 4-3:ArticleRepository

    /**
     * @author：tqzhang on 18/7/28 13:00
     */
     public class ArticleRepository extends BaseRepository {

          public void loadArticleRemList(final CallBack<ArticleVo> listener) {
              addSubscribe(apiService.getArticleRemList()
                    .compose(RxSchedulers.io_main())
                    .subscribe(new RxSubscriber<ArticleVo>() {

                        @Override
                        public void onSuccess(ArticleVo articleObject) {
                            listener.onNext(articleObject);
                        }

                        @Override
                        public void onFailure(String msg) {
                            listener.onError(msg);
                        }
                }));

    }

 最后我们的ArticleRepository中就提供数据，此处只提供了网络层的数据，在实际应用中可拆分类loacl data和remote data,可根据项目需求自行处理。

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

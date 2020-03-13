# takjxhApp
同安会计学会APP，安卓AS开发，Java源生代码。APP开发框架：基于 Material Design + MVP + RxJava + Retrofit + Glide；UI基本遵循Google Material Design设计风格。

详细介绍

一、开发框架：
  APP采用基于 Material Design + MVP + RxJava + Retrofit + Glide的开发框架。

二、主要技术有：
1、项目架构：使用MVP架构模式搭建，封装BaseMvpActivity、BaseMvpFragment、BasePresenter、BaseView。P层绑定V层生命周期 。
2、UI基本遵循Google Material Design设计风格。
3、网络请求：使用Retrofit+RxJava+ okhttp 进行网络请求，封装公共Rerofit的RxHelper(创建RetrofitBuilder、OkHttpClient、addHeader、拦截器、Cookie、https等)、工厂模式创建不同BaseUrl的网络对象。
4、RxJava：包括Rx处理服务器请求、缓存、线程调度。
5、图片加载：Glide加载监听，获取缓存，圆角图片，高斯模糊。
5、组件化开发，横向解耦。
6、封装各种工具类，比如压缩图片、轮播器、查看大图、缓存工具、图片选择。
7、各种封装好的依赖库，比如recyclerview万能适配器、经常使用到的几种Dialog。
8、侧滑菜单：DrawerLayout+NavigationView。
9、使用leakcanary引入检测内存泄漏。
10、标题栏：使用StatuBarUtils沉浸式修改主题。
11、使用FlowLayout实现Android流式布局，支持点击、单选、多选，实现热门标签效果。
12、使用ViewPagerIndicator实现顶部tab，主页底部tab，引导页，无限轮播banner等。
13、使用circleimageview 实现圆形头像。
14、使用butterknife进行依赖注入。
15、使用GsonFormat & Gson json 进行数据解析。
16、使用Logger打印日志。
17、使用eventbus 进行事件发布/订阅框架，组件之间通讯。
18、使用takephoto拍照上0
19、RxAndroid ：用于Android的Rxjava绑定库。
20、RxLifecycle ：防止RxJava中subscription导致内存泄漏。
21、RxPermissions ：基于RxJava开发的用于帮助在Android 6.0中处理运行时权限检测。
22、BaseRecyclerViewAdapterHelper ：RecyclerView多功能适配器。
23、SmartRefreshLayout ：recyclerview下拉刷新、上拉加载。
24、LFilePicker：检索手机目录方式的文件选择器。
25、ViewPager 搭配 Fragment 实现懒加载。
25、使用 ItemTouchHelper 实现应用频道排序、移动等管理。


# IpcService
IPC demo 的服务端，主要是介绍IPC中AIDL和messenger的两种实现方法
需要配合https://github.com/mengchaoshen/IpcClient ipc demo客户端一起使用

主要是参考

 http://blog.csdn.net/zxw136511485/article/details/50815062(AIDL代码中的实际使用)
 
 http://blog.csdn.net/zxw136511485/article/details/53168501(使用messenger实现IPC)
 
这两篇博客写出来的demo

有一些注意点:

## AIDL

根据资料中的例子，亲测AIDL的代码实现，可行，但是有几个注意点：

1. AIDL文件的创建需要使用AS的new file->AIDL->AIDL File，
这样创建的话，会在java同级，创建一个aidl的文件夹（蓝色的），
然后再创建你之前创建好的文件目录，这样才是有效的。build->rebuild一下，就可以使用CalculateAidl类了。

2. Service端创建的时候需要注意，创建的Service，
需要在AndroidManifest.xml里面注册，并且需要设置exported=“true"

3. 启动远端service的时候，包名不要写错

AIDL,实现的client调用service的方法（client与service处于不同的进程process），可以把client的参数传递到service端，但是service端还不能控制client的流程。

## Messenger

根据资料中的例子，亲测Messenger的代码实现，可行，但是有几个注意点：

1. 上述例子时，clinet会出现service intent must be explicit错误，解决方法：http://blog.csdn.net/shenzhonglaoxu/article/details/42675287,大致意思是，android5.0及以上，只能使用explicit显示方法来启动Service,所以上面文章把隐式改为显示就可以成功，或者直接使用显示来启动Service

# Gym
Assignment 4 & 5
members: 薛飞跃 冉旭松

1.	使用sqlite轻型数据库
2.	使用contentprovider实现数据的存取
3.	Sqlite+contentprovider实现将服务器数据缓存到本地，提供离线查看教练信息以及健身文章


登录界面可选择QQ实现第三方登录：
 
点击QQ登录，弹出登录界面：
 
点击登录进入Gym Club主程序：
 
本功能的实现依赖于 腾讯QQ所提供的开发者sdk 。
实现该功能的关键步骤如下：
1.	为GymClub客户端赋予相应的权限。主要是连接网络的权限。
2.	实现第三方登录需要调起开发者SDK中的AuthActivity和AssistActivity。我们要在AndroidManifest中注册这两个活动。
3.	编写Java文件 
	3.1 	添加相应的监听事件，唤醒QQ登录。
	3.2	回调，在唤醒QQ登录后回调类检测是否登录成功，这个类在开发者SDK中已经提供。
	3.3	在上两步中，根据需要重写需要重写的方法。


# Android app 效能優化範例紀錄

## StrictMode example
StrictMode 的運作機制為：
1.偵測異常行為
2.通知異常


## LeakCanary example
LeakCanary主要用來測試是否有Memory leak的工具之一，
好處是可以邊操控邊偵測是否有發生Memory leak，
當有發生錯誤的時候，可以dump heap 內容，
就可以哪一塊哪一部分發生了Memory leak了，
當然也可以針對單一view 物件進行單獨監控，
通常不指定的話，default是全部監看。 


## Coroutine example
利用Coroutine 進行減少Memory Leak發生機率，
以前當要在背景執行續上進行Task的時候，
都會new 一個Thread進行Task內容，
但當此Thread 還未完成的時候(http，大量讀寫IO，等等)，
使用者卻離開當前頁面，
就會發生Memory leak，
而Coroutine 設計上是非同步的、方便處理、不易出錯、減少 Memory leak 機會，
搭配著lifecycleScope 物件，
lifecycleScope 生命週期將與 Activity 的生命週期一致，
所以當 Activity 被銷毀，Coroutine 執行中的任務也將被取消，
也就不會發生當離開 Activity 時，背景執行緒仍在執行。


## Retrofit api example
此範例主要建構基礎簡單的http request架構建置方法，
使用retrofit 實作 api Request，
結合retrofit gson 進行response parser 結果回傳到call api 的使用者，
用此架構比以前使用的 HttpURLConnection 方法還要更方便，
效能上也會更好。





## Reference 文章 : 

> Android app 效能優化(https://ithelp.ithome.com.tw/users/20111896/ironman/5884?page=1)

> StrictMode developers 文章(https://developer.android.com/reference/android/os/StrictMode)
 
> LeakCanary 文章(https://square.github.io/leakcanary/)

> Coroutine 文章(https://kotlinlang.org/docs/coroutines-guide.html)

> Retrofit 文章(https://ithelp.ithome.com.tw/articles/10304921)

# Background Proses dan Thread #


Level prioritas proses:

* Foreground Process : screen yang sedang dipakai user

    * Activity yang sedang aktif
    * Service yang dipanggil (bound) oleh Activity aktif
    * Service yang menjalankan `startForeground()` dan ada tampilannya di notification bar
    * Service yang sedang menjalankan lifecycle method
    * Broadcast receiver yang sedang menjalankan `onReceive()`

* Visible Process : screen yang masih terlihat, tapi bukan paling atas. Misalnya : sedang membuka dialog

* Service Process
* Background Process
* Empty Proses

Beberapa panduan multi threading

* Jangan lakukan proses yang lama di UI Thread
* Beberapa pilihan multithreading:

  * AsyncTask
  * Service


## Referensi ##

* http://stackoverflow.com/questions/6957775/android-asynctask-vs-service
* https://medium.com/android-news/using-intentservice-vs-asynctask-in-android-2fec1b853ff4#.tgqfb48ad
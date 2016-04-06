# Google Cloud Messaging #

Service disediakan oleh Google untuk mengirim data/pesan dari aplikasi server ke aplikasi Android.

Aplikasi Server --> GCM Server --> Device
Device --> GCM Server --> Aplikasi Server


## Istilah ##

* Sender ID : id aplikasi server / username aplikasi server untuk connect ke GCM
* API Key : password aplikasi server untuk connect ke GCM
* Registration token : unique ID yang didapatkan device pada waktu registrasi ke GCM
* Device Group : kumpulan beberapa registration token. Misalnya satu user punya banyak gadget. Device group maksimal 10 device.
* Topic : pengelompokan message yang akan dikirim ke semua subscriber

## Langkah-langkah ##

1. Daftarkan project / aplikasi ke Google Developer Console. Hasilnya : Sender ID dan API Key
2. Download konfigurasi untuk dipasang di project Android
3. Tambahkan library GCM di aplikasi Android
4. Registrasi service dan receiver bawaan GCM di aplikasi android (dalam manifest)
5. Registrasi device ke GCM Server. Hasilnya : registration token (device ID)
6. Kirim registration token ke aplikasi server
7. Simpan registration token, berelasi dengan user aplikasi


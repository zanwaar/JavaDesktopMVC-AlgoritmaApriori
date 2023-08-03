## Java desktop netbeans mvc & Penerapan Algoritma Apriori

## Introduction
Assalamu'alikum...
<br/>
Proyek ini menggunakan implementasi MVC untuk memudahkan pengembangan dalam penerapan Algoritma Apriori pada transaksi barang keluar.
<br/>
Algoritma Apriori adalah algoritma yang digunakan untuk menemukan item-item yang sering muncul bersama dalam sebuah kumpulan data transaksi atau dataset. Algoritma ini banyak digunakan dalam analisis asosiasi, yang mencoba menemukan hubungan antara item-item dalam dataset.
 
## Setup Libraries

suport Java versi 8, 11, 17 : deafult java 8 
<br/>
add libraries mysql atau add jar https://drive.google.com/file/d/18qNF-9YD0S9AVZlINC4aMxrBn41DAn0G/view?usp=sharing

## Setup Database
file DatabaseUtil.java di folder util
<br/>
deafult namaDatabse : db_tokosembakoberkah
        user        : root
        password    : 
<br/>
<br/>
[![saweria](https://github.com/zanwaar/JavaDesktopMVC-AlgoritmaApriori/blob/main/screenshot/sw.svg)](https://saweria.co/Batukel)
## cuplikan beberapa Screenshoot
## Halaman Login
![Alt Text](https://github.com/zanwaar/JavaDesktopMVC-AlgoritmaApriori/blob/main/screenshot/login.JPG)

## Dashboard
![Alt Text](https://github.com/zanwaar/JavaDesktopMVC/blob/main/screenshot/dashboard.JPG)

## Data Transaksi
![Alt Text](https://github.com/zanwaar/JavaDesktopMVC/blob/main/screenshot/transaksi.JPG)

## Transaksi Keluar
![Alt Text](https://github.com/zanwaar/JavaDesktopMVC/blob/main/screenshot/tskeluar.JPG)

## Transaksi Masuk
![Alt Text](https://github.com/zanwaar/JavaDesktopMVC/blob/main/screenshot/tsmasuk.JPG)

{
  "transactions": [
    {
      "Transaksi ID": 55,
      "Barang dalam Transaksi": [
        {
          "Nama Barang": "Beras Medium 5kg"
        },
        {
          "Nama Barang": "Sambal ABC Botol 135g"
        },
        {
          "Nama Barang": "Teh Celup Sariwangi 25 bungkus"
        }
      ]
    },
    {
      "Transaksi ID": 56,
      "Barang dalam Transaksi": [
        {
          "Nama Barang": "Minyak Goreng Bimoli 1 liter"
        },
        {
          "Nama Barang": "Kecap Manis ABC 620ml"
        },
        {
          "Nama Barang": "Kopi Kapal Api Bubuk 200g"
        },
        {
          "Nama Barang": "Roti Tawar Sari Roti"
        }
      ]
    },
    {
      "Transaksi ID": 57,
      "Barang dalam Transaksi": [
        {
          "Nama Barang": "Beras Medium 5kg"
        },
        {
          "Nama Barang": "Kecap Manis ABC 620ml"
        },
        {
          "Nama Barang": "Roti Tawar Sari Roti"
        },
        {
          "Nama Barang": "Kembang Tahu 250g"
        },
        {
          "Nama Barang": "Sagu 500g"
        }
      ]
    },
    {
      "Transaksi ID": 58,
      "Barang dalam Transaksi": [
        {
          "Nama Barang": "Kecap Manis ABC 620ml"
        },
        {
          "Nama Barang": "Teh Celup Sariwangi 25 bungkus"
        },
        {
          "Nama Barang": "Roti Tawar Sari Roti"
        }
      ]
    },
    {
      "Transaksi ID": 59,
      "Barang dalam Transaksi": [
        {
          "Nama Barang": "Minyak Goreng Bimoli 1 liter"
        },
        {
          "Nama Barang": "Sambal ABC Botol 135g"
        }
      ]
    },
    {
      "Transaksi ID": 60,
      "Barang dalam Transaksi": [
        {
          "Nama Barang": "Sambal ABC Botol 135g"
        },
        {
          "Nama Barang": "Sarden Kaleng 425g"
        },
        {
          "Nama Barang": "Kembang Tahu 250g"
        },
        {
          "Nama Barang": "Sagu 500g"
        }
      ]
    },
    {
      "Transaksi ID": 61,
      "Barang dalam Transaksi": [
        {
          "Nama Barang": "Kecap Manis ABC 620ml"
        },
        {
          "Nama Barang": "Roti Tawar Sari Roti"
        }
      ]
    },
    {
      "Transaksi ID": 62,
      "Barang dalam Transaksi": [
        {
          "Nama Barang": "Kopi Kapal Api Bubuk 200g"
        },
        {
          "Nama Barang": "Kembang Tahu 250g"
        }
      ]
    },
    {
      "Transaksi ID": 63,
      "Barang dalam Transaksi": [
        {
          "Nama Barang": "Teh Celup Sariwangi 25 bungkus"
        },
        {
          "Nama Barang": "Kembang Tahu 250g"
        },
        {
          "Nama Barang": "Sagu 500g"
        }
      ]
    },
    {
      "Transaksi ID": 64,
      "Barang dalam Transaksi": [
        {
          "Nama Barang": "Beras Medium 5kg"
        },
        {
          "Nama Barang": "Kecap Manis ABC 620ml"
        }
      ]
    },
    {
      "Transaksi ID": 65,
      "Barang dalam Transaksi": [
        {
          "Nama Barang": "Minyak Goreng Bimoli 1 liter"
        },
        {
          "Nama Barang": "Sambal ABC Botol 135g"
        },
        {
          "Nama Barang": "Sarden Kaleng 425g"
        },
        {
          "Nama Barang": "Kembang Tahu 250g"
        },
        {
          "Nama Barang": "Sagu 500g"
        }
      ]
    },
    {
      "Transaksi ID": 66,
      "Barang dalam Transaksi": [
        {
          "Nama Barang": "Minyak Goreng Bimoli 1 liter"
        },
        {
          "Nama Barang": "Kecap Manis ABC 620ml"
        },
        {
          "Nama Barang": "Kopi Kapal Api Bubuk 200g"
        },
        {
          "Nama Barang": "Roti Tawar Sari Roti"
        }
      ]
    },
    {
      "Transaksi ID": 67,
      "Barang dalam Transaksi": [
        {
          "Nama Barang": "Sambal ABC Botol 135g"
        },
        {
          "Nama Barang": "Roti Tawar Sari Roti"
        },
        {
          "Nama Barang": "Kembang Tahu 250g"
        },
        {
          "Nama Barang": "Sagu 500g"
        }
      ]
    },
    {
      "Transaksi ID": 68,
      "Barang dalam Transaksi": [
        {
          "Nama Barang": "Kecap Manis ABC 620ml"
        },
        {
          "Nama Barang": "Teh Celup Sariwangi 25 bungkus"
        },
        {
          "Nama Barang": "Roti Tawar Sari Roti"
        }
      ]
    },
    {
      "Transaksi ID": 69,
      "Barang dalam Transaksi": [
        {
          "Nama Barang": "Beras Medium 5kg"
        },
        {
          "Nama Barang": "Kembang Tahu 250g"
        },
        {
          "Nama Barang": "Sagu 500g"
        }
      ]
    },
    {
      "Transaksi ID": 70,
      "Barang dalam Transaksi": [
        {
          "Nama Barang": "Sambal ABC Botol 135g"
        },
        {
          "Nama Barang": "Sarden Kaleng 425g"
        },
        {
          "Nama Barang": "Kembang Tahu 250g"
        },
        {
          "Nama Barang": "Sagu 500g"
        }
      ]
    },
    {
      "Transaksi ID": 71,
      "Barang dalam Transaksi": [
        {
          "Nama Barang": "Kecap Manis ABC 620ml"
        },
        {
          "Nama Barang": "Teh Celup Sariwangi 25 bungkus"
        },
        {
          "Nama Barang": "Roti Tawar Sari Roti"
        }
      ]
    },
    {
      "Transaksi ID": 72,
      "Barang dalam Transaksi": [
        {
          "Nama Barang": "Kopi Kapal Api Bubuk 200g"
        },
        {
          "Nama Barang": "Kembang Tahu 250g"
        }
      ]
    },
    {
      "Transaksi ID": 73,
      "Barang dalam Transaksi": [
        {
          "Nama Barang": "Kopi Kapal Api Bubuk 200g"
        },
        {
          "Nama Barang": "Kembang Tahu 250g"
        },
        {
          "Nama Barang": "Sagu 500g"
        }
      ]
    },
    {
      "Transaksi ID": 74,
      "Barang dalam Transaksi": [
        {
          "Nama Barang": "Beras Medium 5kg"
        },
        {
          "Nama Barang": "Kecap Manis ABC 620ml"
        },
        {
          "Nama Barang": "Roti Tawar Sari Roti"
        }
      ]
    }
  ]
}
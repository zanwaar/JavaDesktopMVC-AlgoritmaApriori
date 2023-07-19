-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 19, 2023 at 04:31 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_tokosembakoberkah`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id` int(11) NOT NULL,
  `kode` varchar(25) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `kategori` varchar(100) NOT NULL,
  `satuan` varchar(20) NOT NULL,
  `stok` int(11) NOT NULL,
  `hargaSatuan` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id`, `kode`, `nama`, `kategori`, `satuan`, `stok`, `hargaSatuan`) VALUES
(44, 'BS', 'beras', 'sembako', 'karung', 40, 10000),
(46, 'S1', 'susu', 'sembako', 'kardus', 995, 250000),
(47, 'TK1', 'Telur', 'Telur', 'rak', 300, 240000),
(48, 'MTG', 'Mentega bkueband', 'Mentega', 'Kardus', 10055, 20000),
(49, 'GlA-12', 'Gula', 'Gula', 'Karung', 1060, 20000);

-- --------------------------------------------------------

--
-- Table structure for table `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id` int(11) NOT NULL,
  `id_transaksi` int(11) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `kategori` varchar(50) NOT NULL,
  `satuan` int(50) NOT NULL,
  `jumlah` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id`, `id_transaksi`, `nama_barang`, `kategori`, `satuan`, `jumlah`) VALUES
(54, 32, 'Gula', 'Gula', 1000, 20000000),
(55, 33, 'Mentega bkueband', 'Mentega', 44, 880000),
(56, 34, 'susu', 'sembako', 6, 1440000),
(57, 35, 'beras', 'sembako', 5, 50000),
(58, 36, 'Gula', 'Gula', 7, 140000),
(59, 37, 'susu', 'sembako', 2, 500000),
(60, 38, 'Gula', 'Gula', 6, 120000),
(61, 39, 'Gula', 'Gula', 7, 140000),
(62, 40, 'Gula', 'Gula', 6, 120000),
(63, 41, 'Gula', 'Gula', 5, 100000),
(64, 42, 'beras', 'sembako', 2, 20000),
(65, 43, 'Mentega bkueband', 'Mentega', 1, 20000),
(66, 44, 'Gula', 'Gula', 4, 80000),
(67, 44, 'susu', 'sembako', 6, 1500000),
(68, 45, 'beras', 'sembako', 3, 30000),
(69, 46, 'Gula', 'Gula', 4, 80000),
(70, 46, 'susu', 'sembako', 6, 1500000),
(71, 47, 'Gula', 'Gula', 7, 140000),
(72, 48, 'susu', 'sembako', 1000, 250000000),
(73, 49, 'susu', 'sembako', 5, 1250000),
(74, 49, 'Gula', 'Gula', 3, 60000),
(75, 50, 'Telur', 'Telur', 300, 72000000),
(76, 51, 'Telur', 'Telur', 50, 12000000),
(77, 52, 'Telur', 'Telur', 50, 12000000);

-- --------------------------------------------------------

--
-- Table structure for table `entitas`
--

CREATE TABLE `entitas` (
  `id` int(11) NOT NULL,
  `type` varchar(10) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(200) NOT NULL,
  `no_telp` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `entitas`
--

INSERT INTO `entitas` (`id`, `type`, `nama`, `alamat`, `no_telp`) VALUES
(0, '-', '-', '-', '-'),
(1, 'Supplier', 'alan jamaludin', 'jln mujair', '081239981831'),
(2, 'pelanggan', 'iwan', 'batukel pasar binaya', '08122383747457'),
(9, 'pelanggan', 'reno', 'alamat', 'p09087968'),
(13, 'Supplier', 'reno', 'adfafd', '3465376'),
(14, 'Supplier', 'tes', 'qwq', '12');

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `no_rak` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id` int(11) NOT NULL,
  `status` varchar(25) NOT NULL,
  `idUser` int(11) NOT NULL,
  `idSp` int(11) NOT NULL,
  `invoice` varchar(50) NOT NULL,
  `subTotal` int(255) NOT NULL,
  `tanggal` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id`, `status`, `idUser`, `idSp`, `invoice`, `subTotal`, `tanggal`) VALUES
(32, 'Barang Masuk', 1, 0, 'INV-BM-2023070718584035154', 20000000, '2023-07-07'),
(33, 'Barang Keluar', 1, 0, 'INV-BM-2023070719103442725', 880000, '2023-07-07'),
(34, 'Barang Keluar', 1, 0, 'INV-BM-2023070719110819638', 1440000, '2023-07-07'),
(35, 'Barang Keluar', 1, 0, 'INV-BM-2023070719114839147', 50000, '2023-07-07'),
(36, 'Barang Keluar', 1, 0, 'INV-BM-2023070719120042105', 140000, '2023-07-07'),
(37, 'Barang Keluar', 1, 0, 'INV-BM-2023070719121864598', 500000, '2023-07-07'),
(38, 'Barang Masuk', 1, 0, 'INV-BM-2023070719124749962', 120000, '2023-07-07'),
(39, 'Barang Keluar', 1, 0, 'INV-BM-2023070719125981832', 140000, '2023-07-07'),
(40, 'Barang Keluar', 1, 0, 'INV-BM-2023070719131117824', 120000, '2023-07-07'),
(41, 'Barang Keluar', 1, 0, 'INV-BM-2023070720224180833', 100000, '2023-07-07'),
(42, 'Barang Keluar', 1, 0, 'INV-BM-2023070720334271925', 20000, '2023-07-07'),
(43, 'Barang Keluar', 1, 0, 'INV-BM-2023070720340991714', 20000, '2023-07-07'),
(44, 'Barang Keluar', 1, 0, 'INV-BM-2023070720345070648', 1580000, '2023-07-07'),
(45, 'Barang Keluar', 1, 0, 'INV-BM-2023070720404132647', 30000, '2023-07-07'),
(46, 'Barang Keluar', 1, 0, 'INV-BM-2023070720421032244', 1580000, '2023-07-07'),
(47, 'Barang Keluar', 1, 0, 'INV-BM-2023070720423088210', 140000, '2023-07-07'),
(48, 'Barang Masuk', 1, 0, 'INV-BM-2023070720430221656', 250000000, '2023-07-07'),
(49, 'Barang Keluar', 1, 0, 'INV-BM-2023070720442891164', 1310000, '2023-07-07'),
(50, 'Barang Masuk', 1, 0, 'INV-BM-2023070721000672214', 72000000, '2023-07-07'),
(51, 'Barang Masuk', 1, 0, 'INV-BM-2023070721010353069', 12000000, '2023-07-07'),
(52, 'Barang Keluar', 1, 0, 'INV-BM-2023070721012066267', 12000000, '2023-07-07');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `nama` varchar(200) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `nama`, `username`, `password`, `status`) VALUES
(1, 'petugas', 'demo', 'demo', 'aktif');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id`),
  ADD KEY `myKey` (`kategori`);

--
-- Indexes for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `entitas`
--
ALTER TABLE `entitas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- AUTO_INCREMENT for table `entitas`
--
ALTER TABLE `entitas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

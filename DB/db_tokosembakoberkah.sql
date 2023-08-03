-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 03, 2023 at 02:10 AM
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
(51, 'J', 'Sagu 500g', 'Sagu', 'gram', 1, 5000),
(52, 'I', 'Kembang Tahu 250g', 'Kembang Tahu', 'Gram', 14, 3000),
(53, 'H', 'Roti Tawar Sari Roti', 'Roti', 'bungkus', 40, 12000),
(54, 'G', 'Kopi Kapal Api Bubuk 200g', 'Kopi', 'gram', 23, 18000),
(55, 'F', 'Teh Celup Sariwangi 25 bungkus', 'Teh', 'bungkus', 75, 15000),
(56, 'E', 'Sarden Kaleng 425g', 'Sarden', 'gram', 10, 9000),
(57, 'D', 'Sambal ABC Botol 135g', 'Sambal', 'gram', 19, 7000),
(58, 'C', 'Kecap Manis ABC 620ml', 'Kecap Manis', 'ml', 47, 10000),
(59, 'B', 'Minyak Goreng Bimoli 1 liter', 'Minyak Goreng', 'liter', 39, 20000),
(60, 'A', 'Beras Medium 5kg', 'Beras', 'kg', 91, 50000);

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
(80, 55, 'Beras Medium 5kg', 'Beras', 2, 100000),
(81, 55, 'Sambal ABC Botol 135g', 'Sambal', 1, 7000),
(82, 55, 'Teh Celup Sariwangi 25 bungkus', 'Teh', 2, 30000),
(83, 56, 'Minyak Goreng Bimoli 1 liter', 'Minyak Goreng', 2, 40000),
(84, 56, 'Kecap Manis ABC 620ml', 'Kecap Manis', 3, 30000),
(85, 56, 'Kopi Kapal Api Bubuk 200g', 'Kopi', 1, 18000),
(86, 56, 'Roti Tawar Sari Roti', 'Roti', 1, 12000),
(87, 57, 'Beras Medium 5kg', 'Beras', 1, 50000),
(88, 57, 'Kecap Manis ABC 620ml', 'Kecap Manis', 2, 20000),
(89, 57, 'Roti Tawar Sari Roti', 'Roti', 2, 24000),
(90, 57, 'Kembang Tahu 250g', 'Kembang Tahu', 2, 6000),
(91, 57, 'Sagu 500g', 'Sagu', 2, 10000),
(92, 58, 'Kecap Manis ABC 620ml', 'Kecap Manis', 2, 20000),
(93, 58, 'Teh Celup Sariwangi 25 bungkus', 'Teh', 2, 30000),
(94, 58, 'Roti Tawar Sari Roti', 'Roti', 4, 48000),
(95, 59, 'Minyak Goreng Bimoli 1 liter', 'Minyak Goreng', 4, 80000),
(96, 59, 'Sambal ABC Botol 135g', 'Sambal', 1, 7000),
(97, 60, 'Sambal ABC Botol 135g', 'Sambal', 4, 28000),
(98, 60, 'Sarden Kaleng 425g', 'Sarden', 2, 18000),
(99, 60, 'Kembang Tahu 250g', 'Kembang Tahu', 6, 18000),
(100, 60, 'Sagu 500g', 'Sagu', 3, 15000),
(101, 61, 'Kecap Manis ABC 620ml', 'Kecap Manis', 4, 40000),
(102, 61, 'Roti Tawar Sari Roti', 'Roti', 4, 48000),
(103, 62, 'Kopi Kapal Api Bubuk 200g', 'Kopi', 3, 54000),
(104, 62, 'Kembang Tahu 250g', 'Kembang Tahu', 1, 3000),
(105, 63, 'Teh Celup Sariwangi 25 bungkus', 'Teh', 4, 60000),
(106, 63, 'Kembang Tahu 250g', 'Kembang Tahu', 5, 15000),
(107, 63, 'Sagu 500g', 'Sagu', 2, 10000),
(108, 64, 'Beras Medium 5kg', 'Beras', 3, 150000),
(109, 64, 'Kecap Manis ABC 620ml', 'Kecap Manis', 3, 30000),
(110, 65, 'Minyak Goreng Bimoli 1 liter', 'Minyak Goreng', 3, 60000),
(111, 65, 'Sambal ABC Botol 135g', 'Sambal', 4, 28000),
(112, 65, 'Sarden Kaleng 425g', 'Sarden', 2, 18000),
(113, 65, 'Kembang Tahu 250g', 'Kembang Tahu', 6, 18000),
(114, 65, 'Sagu 500g', 'Sagu', 7, 35000),
(115, 66, 'Minyak Goreng Bimoli 1 liter', 'Minyak Goreng', 2, 40000),
(116, 66, 'Kecap Manis ABC 620ml', 'Kecap Manis', 4, 40000),
(117, 66, 'Kopi Kapal Api Bubuk 200g', 'Kopi', 5, 90000),
(118, 66, 'Roti Tawar Sari Roti', 'Roti', 6, 72000),
(119, 67, 'Sambal ABC Botol 135g', 'Sambal', 5, 35000),
(120, 67, 'Roti Tawar Sari Roti', 'Roti', 2, 24000),
(121, 67, 'Kembang Tahu 250g', 'Kembang Tahu', 4, 12000),
(122, 67, 'Sagu 500g', 'Sagu', 2, 10000),
(123, 68, 'Kecap Manis ABC 620ml', 'Kecap Manis', 3, 30000),
(124, 68, 'Teh Celup Sariwangi 25 bungkus', 'Teh', 2, 30000),
(125, 68, 'Roti Tawar Sari Roti', 'Roti', 2, 24000),
(126, 69, 'Beras Medium 5kg', 'Beras', 2, 100000),
(127, 69, 'Kembang Tahu 250g', 'Kembang Tahu', 5, 15000),
(128, 69, 'Sagu 500g', 'Sagu', 6, 30000),
(129, 70, 'Sambal ABC Botol 135g', 'Sambal', 6, 42000),
(130, 70, 'Sarden Kaleng 425g', 'Sarden', 6, 54000),
(131, 70, 'Kembang Tahu 250g', 'Kembang Tahu', 5, 15000),
(132, 70, 'Sagu 500g', 'Sagu', 1, 5000),
(133, 71, 'Kecap Manis ABC 620ml', 'Kecap Manis', 1, 10000),
(134, 71, 'Teh Celup Sariwangi 25 bungkus', 'Teh', 5, 75000),
(135, 71, 'Roti Tawar Sari Roti', 'Roti', 3, 36000),
(136, 72, 'Kopi Kapal Api Bubuk 200g', 'Kopi', 2, 36000),
(137, 72, 'Kembang Tahu 250g', 'Kembang Tahu', 1, 3000),
(138, 73, 'Kopi Kapal Api Bubuk 200g', 'Kopi', 1, 18000),
(139, 73, 'Kembang Tahu 250g', 'Kembang Tahu', 1, 3000),
(140, 73, 'Sagu 500g', 'Sagu', 1, 5000),
(141, 74, 'Beras Medium 5kg', 'Beras', 1, 50000),
(142, 74, 'Kecap Manis ABC 620ml', 'Kecap Manis', 1, 10000),
(143, 74, 'Roti Tawar Sari Roti', 'Roti', 1, 12000);

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
(2, 'pelanggan', 'iwan', 'batukel pasar binaya', '08122383747457');

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
(55, 'Barang Keluar', 1, 0, 'INV-BM-2023072816284018784', 137000, '2023-07-28'),
(56, 'Barang Keluar', 1, 0, 'INV-BM-2023072816292368182', 100000, '2023-07-28'),
(57, 'Barang Keluar', 1, 0, 'INV-BM-2023072816310024584', 110000, '2023-07-28'),
(58, 'Barang Keluar', 1, 0, 'INV-BM-2023072816313963799', 98000, '2023-07-28'),
(59, 'Barang Keluar', 1, 0, 'INV-BM-2023072816320833023', 87000, '2023-07-28'),
(60, 'Barang Keluar', 1, 0, 'INV-BM-2023072816325925123', 79000, '2023-07-28'),
(61, 'Barang Keluar', 1, 0, 'INV-BM-2023072816331957780', 88000, '2023-07-28'),
(62, 'Barang Keluar', 1, 0, 'INV-BM-2023072816334163510', 57000, '2023-07-28'),
(63, 'Barang Keluar', 1, 0, 'INV-BM-2023072816342589983', 85000, '2023-07-28'),
(64, 'Barang Keluar', 1, 0, 'INV-BM-2023072816345343317', 180000, '2023-07-28'),
(65, 'Barang Keluar', 1, 0, 'INV-BM-2023072816354356568', 159000, '2023-07-28'),
(66, 'Barang Keluar', 1, 0, 'INV-BM-2023072816365029369', 242000, '2023-07-28'),
(67, 'Barang Keluar', 1, 0, 'INV-BM-2023072816375138569', 81000, '2023-07-28'),
(68, 'Barang Keluar', 1, 0, 'INV-BM-2023072816383396433', 84000, '2023-07-28'),
(69, 'Barang Keluar', 1, 0, 'INV-BM-2023072816390824439', 145000, '2023-07-28'),
(70, 'Barang Keluar', 1, 0, 'INV-BM-2023072816403064520', 116000, '2023-07-28'),
(71, 'Barang Keluar', 1, 0, 'INV-BM-2023072816414686802', 121000, '2023-07-28'),
(72, 'Barang Keluar', 1, 0, 'INV-BM-2023072816422184537', 39000, '2023-07-28'),
(73, 'Barang Keluar', 1, 0, 'INV-BM-2023072816424663315', 26000, '2023-07-28'),
(74, 'Barang Keluar', 1, 0, 'INV-BM-2023072816431007124', 72000, '2023-07-28');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=144;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=75;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

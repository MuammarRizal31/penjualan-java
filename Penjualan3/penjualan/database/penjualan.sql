-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 06, 2020 at 03:42 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.2.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `penjualan`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `KodeBarang` varchar(15) NOT NULL,
  `NamaBarang` varchar(50) NOT NULL,
  `HargaBeli` int(30) NOT NULL,
  `HargaJual` int(30) NOT NULL,
  `Stock` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`KodeBarang`, `NamaBarang`, `HargaBeli`, `HargaJual`, `Stock`) VALUES
('B001', 'suntikan', 200000, 250000, '20'),
('B002', 'obat', 20000, 25000, '20'),
('B003', 'kesehatan', 300000, 400000, '20'),
('B004', 'alat tes gula', 500000, 600000, '30'),
('B005', 'tensimeter', 600000, 700000, '30'),
('B006', 'Pulse oximeter', 1000000, 1200000, '25'),
('B007', 'tabung osigen', 600000, 700000, '25'),
('B008', 'Alat Infus (Infuse Set)', 600000, 700000, '20'),
('B009', 'alat cek darah tinggi', 200000, 300000, '0'),
('B010', 'alat cek darah rendah', 500000, 600000, '0');

-- --------------------------------------------------------

--
-- Table structure for table `beli`
--

CREATE TABLE `beli` (
  `NoBeli` varchar(20) NOT NULL,
  `KodeBarang` varchar(50) NOT NULL,
  `NamaBarang` varchar(50) NOT NULL,
  `TanggalBeli` date NOT NULL,
  `HargaBeli` int(20) NOT NULL,
  `Jumlah` int(20) NOT NULL,
  `TotalHarga` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `beli`
--

INSERT INTO `beli` (`NoBeli`, `KodeBarang`, `NamaBarang`, `TanggalBeli`, `HargaBeli`, `Jumlah`, `TotalHarga`) VALUES
('P001', 'B003', 'kesehatan', '2020-08-01', 300000, 5, 1500000),
('P002', 'B003', 'kesehatan', '2020-08-05', 300000, 10, 3000000),
('P003', 'B001', 'suntikan', '2020-08-05', 200000, 20, 4000000),
('P004', 'B003', 'kesehatan', '2020-08-05', 300000, 15, 4500000),
('P005', 'B004', 'alat tes gula', '2020-08-23', 500000, 30, 15000000),
('P006', 'B005', 'tensimeter', '2020-08-26', 600000, 30, 18000000),
('P007', 'B006', 'Pulse oximeter', '2020-08-26', 1000000, 30, 30000000),
('P008', 'B007', 'tabung osigen', '2020-08-26', 600000, 30, 18000000),
('P009', 'B008', 'Alat Infus (Infuse Set)', '2020-08-28', 600000, 40, 24000000),
('P010', 'B009', ' Kursi Roda', '2020-08-31', 1000000, 30, 30000000),
('P011', 'B008', 'Alat Infus (Infuse Set)', '2020-08-26', 600000, 20, 12000000),
('P012', 'B002', 'obat', '2020-08-30', 20000, 10, 200000),
('P013', 'B008', 'Alat Infus (Infuse Set)', '2020-08-20', 600000, 30, 18000000),
('P014', 'B010', 'suntikan2', '2020-08-25', 100000, 30, 3000000),
('P015', 'B011', 'suntikan4', '2020-08-26', 200000, 30, 6000000);

-- --------------------------------------------------------

--
-- Table structure for table `jual`
--

CREATE TABLE `jual` (
  `KodeTransaksi` varchar(20) NOT NULL,
  `TanggalTransaksi` date NOT NULL,
  `Nama` varchar(50) NOT NULL,
  `KodeBarang` varchar(20) NOT NULL,
  `NamaBarang` varchar(50) NOT NULL,
  `HargaJual` int(20) NOT NULL,
  `Jumlah` int(20) NOT NULL,
  `TotalHarga` int(20) NOT NULL,
  `UangBayar` int(20) NOT NULL,
  `UangKembali` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `jual`
--

INSERT INTO `jual` (`KodeTransaksi`, `TanggalTransaksi`, `Nama`, `KodeBarang`, `NamaBarang`, `HargaJual`, `Jumlah`, `TotalHarga`, `UangBayar`, `UangKembali`) VALUES
('TP001', '2020-07-01', 'CV ANDALA JAYA', 'B002', 'obat', 25000, 5, 125000, 200000, 75000),
('TP002', '2020-08-13', 'CV ANDALA JAYA', 'B002', 'obat', 25000, 15, 375000, 400000, 25000),
('TP003', '2020-08-20', 'rs .sederhana', 'B001', 'suntikan', 250000, 10, 2500000, 3000000, 500000),
('TP004', '2020-08-19', 'cv makmur  jaya', 'B002', 'obat', 25000, 20, 500000, 500000, 0),
('TP005', '2020-08-28', 'cv makmur  jaya', 'B008', 'Alat Infus (Infuse Set)', 700000, 11, 7700000, 8000000, 300000),
('TP006', '2020-08-19', 'cv galang rambu', 'B008', 'Alat Infus (Infuse Set)', 700000, 6, 4200000, 5000000, 800000),
('TP007', '2020-08-19', 'cv makmur  jaya', 'B008', 'Alat Infus (Infuse Set)', 700000, 3, 2100000, 0, 0),
('TP008', '2020-08-05', ' Cv Bangun Jaya', 'B008', 'Alat Infus (Infuse Set)', 700000, 3, 2100000, 3000000, 900000),
('TP009', '2020-08-05', ' Cv Bangun Jaya', 'B007', 'tabung osigen', 700000, 5, 3500000, 4000000, 500000),
('TP010', '2020-08-05', ' Cv Bangun Jaya', 'B006', 'Pulse oximeter', 1200000, 5, 6000000, 6000000, 0),
('TP011', '2020-08-05', 'cv makmur  jaya', 'B008', 'Alat Infus (Infuse Set)', 700000, 7, 4900000, 5000000, 100000);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `NoId` varchar(20) NOT NULL,
  `UserName` varchar(50) NOT NULL,
  `Nama` varchar(50) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `HakAkses` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`NoId`, `UserName`, `Nama`, `Password`, `HakAkses`) VALUES
('U001', 'kasir', 'ajat', '123456', 'Kasir'),
('U002', 'pemilik', 'ajat', '123456', 'Pemilik'),
('U003', 'persedia', 'ajat', '123456', 'Persediaan');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `KodeSupplier` varchar(15) NOT NULL,
  `Nama` varchar(50) NOT NULL,
  `NoTelepon` varchar(20) NOT NULL,
  `Alamat` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`KodeSupplier`, `Nama`, `NoTelepon`, `Alamat`) VALUES
('S001', 'CV ANDALA JAYA', '021765454254', 'Jl.sereseng sawah No .23 jakarta selatan'),
('S002', 'rs .sederhana', '02198763567', 'jl dekat raya no 32 jakarta selatan'),
('S003', 'cv makmur adil', '083456217665635', 'jl makmur adil no 31 jakarta selatan'),
('S004', 'klink restu ibu', '067647362767', 'jl swada raya no 32 jakarta selatan'),
('S005', 'cv galang rambu', '07383276372', 'jl lebak sari raya n055'),
('S006', 'cv makmur', '087627166726', 'jl.lebak sari  raya'),
('S007', 'cv makmur  jaya', '08627637676', 'jl lebak sari raya'),
('S008', ' Cv Bangun Jaya', '0219873637636', 'jl poltangan raya no 23 ');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`KodeBarang`);

--
-- Indexes for table `beli`
--
ALTER TABLE `beli`
  ADD PRIMARY KEY (`NoBeli`);

--
-- Indexes for table `jual`
--
ALTER TABLE `jual`
  ADD PRIMARY KEY (`KodeTransaksi`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`NoId`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`KodeSupplier`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

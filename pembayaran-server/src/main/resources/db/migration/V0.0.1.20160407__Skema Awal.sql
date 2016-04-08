CREATE TABLE `m_user` (
  `id` varchar(255) NOT NULL,
  `terakhir_update` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `nomer_handphone` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rycw44p7cruupkosx3ibmj9q3` (`email`),
  UNIQUE KEY `UK_brmb54ld0gqstogmb812xw951` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `m_user` WRITE;
/*!40000 ALTER TABLE `m_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_user` ENABLE KEYS */;
UNLOCK TABLES;

CREATE TABLE `m_handphone` (
  `id` varchar(255) NOT NULL,
  `terakhir_update` datetime NOT NULL,
  `gcm_token` varchar(255) NOT NULL,
  `id_user` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_n234f6d67sgkffkn79ck01mh3` (`id_user`),
  CONSTRAINT `FK_n234f6d67sgkffkn79ck01mh3` FOREIGN KEY (`id_user`) REFERENCES `m_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `m_handphone` WRITE;
/*!40000 ALTER TABLE `m_handphone` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_handphone` ENABLE KEYS */;
UNLOCK TABLES;

CREATE TABLE `m_produk` (
  `id` varchar(255) NOT NULL,
  `terakhir_update` datetime NOT NULL,
  `kode` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9ev77sfyb2omfjx1ugrdcgftm` (`kode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `m_produk` WRITE;
UNLOCK TABLES;

CREATE TABLE `m_user_password` (
  `id` varchar(255) NOT NULL,
  `terakhir_update` datetime NOT NULL,
  `hashed_password` varchar(255) NOT NULL,
  `id_user` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m2a93fmrjgvvkowobo3lixd39` (`id_user`),
  CONSTRAINT `FK_m2a93fmrjgvvkowobo3lixd39` FOREIGN KEY (`id_user`) REFERENCES `m_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `m_user_password` WRITE;
/*!40000 ALTER TABLE `m_user_password` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_user_password` ENABLE KEYS */;
UNLOCK TABLES;

CREATE TABLE `t_gcm_outgoing_message` (
  `id` varchar(255) NOT NULL,
  `terakhir_update` datetime NOT NULL,
  `collapse_key` varchar(255) DEFAULT NULL,
  `data` varchar(255) DEFAULT NULL,
  `failed_message` varchar(255) DEFAULT NULL,
  `gcm_id` varchar(255) DEFAULT NULL,
  `notification` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `ttl` int(11) DEFAULT NULL,
  `message_to` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


LOCK TABLES `t_gcm_outgoing_message` WRITE;
/*!40000 ALTER TABLE `t_gcm_outgoing_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_gcm_outgoing_message` ENABLE KEYS */;
UNLOCK TABLES;

CREATE TABLE `t_tagihan` (
  `id` varchar(255) NOT NULL,
  `terakhir_update` datetime NOT NULL,
  `admin` decimal(19,2) NOT NULL,
  `denda` decimal(19,2) NOT NULL,
  `jatuh_tempo` date NOT NULL,
  `pajak` decimal(19,2) NOT NULL,
  `periode_tagihan` date NOT NULL,
  `tagihan` decimal(19,2) NOT NULL,
  `id_produk` varchar(255) NOT NULL,
  `id_user` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gq8pvjcos5d8wk29m8jn3425j` (`id_produk`),
  KEY `FK_qssbigmpix7dl6fe9c2tbtjlh` (`id_user`),
  CONSTRAINT `FK_gq8pvjcos5d8wk29m8jn3425j` FOREIGN KEY (`id_produk`) REFERENCES `m_produk` (`id`),
  CONSTRAINT `FK_qssbigmpix7dl6fe9c2tbtjlh` FOREIGN KEY (`id_user`) REFERENCES `m_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


LOCK TABLES `t_tagihan` WRITE;

UNLOCK TABLES;


CREATE TABLE `t_pembayaran` (
  `id` varchar(255) NOT NULL,
  `terakhir_update` datetime NOT NULL,
  `nilai` decimal(19,2) NOT NULL,
  `waktu_transaksi` datetime NOT NULL,
  `id_tagihan` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1gho76p3grval4so0p5kyebib` (`id_tagihan`),
  CONSTRAINT `FK_1gho76p3grval4so0p5kyebib` FOREIGN KEY (`id_tagihan`) REFERENCES `t_tagihan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `t_pembayaran` WRITE;
/*!40000 ALTER TABLE `t_pembayaran` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_pembayaran` ENABLE KEYS */;
UNLOCK TABLES;
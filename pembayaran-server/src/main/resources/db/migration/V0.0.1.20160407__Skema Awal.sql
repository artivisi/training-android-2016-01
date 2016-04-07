--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.1
-- Dumped by pg_dump version 9.5.1

--
-- Name: m_handphone; Type: TABLE; Schema: public; Owner: pembayaran
--

CREATE TABLE m_handphone (
    id character varying(255) NOT NULL,
    terakhir_update timestamp without time zone NOT NULL,
    gcm_token character varying(255) NOT NULL,
    id_user character varying(255) NOT NULL
);


ALTER TABLE m_handphone OWNER TO pembayaran;

--
-- Name: m_produk; Type: TABLE; Schema: public; Owner: pembayaran
--

CREATE TABLE m_produk (
    id character varying(255) NOT NULL,
    terakhir_update timestamp without time zone NOT NULL,
    kode character varying(255) NOT NULL,
    nama character varying(255) NOT NULL
);


ALTER TABLE m_produk OWNER TO pembayaran;

--
-- Name: m_user; Type: TABLE; Schema: public; Owner: pembayaran
--

CREATE TABLE m_user (
    id character varying(255) NOT NULL,
    terakhir_update timestamp without time zone NOT NULL,
    email character varying(255) NOT NULL,
    fullname character varying(255) NOT NULL,
    nomer_handphone character varying(255) NOT NULL,
    username character varying(255) NOT NULL
);


ALTER TABLE m_user OWNER TO pembayaran;

--
-- Name: m_user_password; Type: TABLE; Schema: public; Owner: pembayaran
--

CREATE TABLE m_user_password (
    id character varying(255) NOT NULL,
    terakhir_update timestamp without time zone NOT NULL,
    hashed_password character varying(255) NOT NULL,
    id_user character varying(255) NOT NULL
);


ALTER TABLE m_user_password OWNER TO pembayaran;

--
-- Name: t_gcm_outgoing_message; Type: TABLE; Schema: public; Owner: pembayaran
--

CREATE TABLE t_gcm_outgoing_message (
    id character varying(255) NOT NULL,
    collapse_key character varying(255),
    data character varying(255),
    gcm_id character varying(255),
    notification character varying(255),
    status character varying(255) NOT NULL,
    ttl integer,
    message_to character varying(255) NOT NULL
);


ALTER TABLE t_gcm_outgoing_message OWNER TO pembayaran;

--
-- Name: t_pembayaran; Type: TABLE; Schema: public; Owner: pembayaran
--

CREATE TABLE t_pembayaran (
    id character varying(255) NOT NULL,
    terakhir_update timestamp without time zone NOT NULL,
    nilai numeric(19,2) NOT NULL,
    waktu_transaksi timestamp without time zone NOT NULL,
    id_tagihan character varying(255) NOT NULL,
    CONSTRAINT t_pembayaran_nilai_check CHECK ((nilai >= (0)::numeric))
);


ALTER TABLE t_pembayaran OWNER TO pembayaran;

--
-- Name: t_tagihan; Type: TABLE; Schema: public; Owner: pembayaran
--

CREATE TABLE t_tagihan (
    id character varying(255) NOT NULL,
    terakhir_update timestamp without time zone NOT NULL,
    admin numeric(19,2) NOT NULL,
    denda numeric(19,2) NOT NULL,
    jatuh_tempo date NOT NULL,
    pajak numeric(19,2) NOT NULL,
    periode_tagihan date NOT NULL,
    tagihan numeric(19,2) NOT NULL,
    id_produk character varying(255) NOT NULL,
    id_user character varying(255) NOT NULL,
    CONSTRAINT t_tagihan_admin_check CHECK ((admin >= (0)::numeric)),
    CONSTRAINT t_tagihan_denda_check CHECK ((denda >= (0)::numeric)),
    CONSTRAINT t_tagihan_pajak_check CHECK ((pajak >= (0)::numeric)),
    CONSTRAINT t_tagihan_tagihan_check CHECK ((tagihan >= (0)::numeric))
);


ALTER TABLE t_tagihan OWNER TO pembayaran;

--
-- Name: m_handphone_pkey; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY m_handphone
    ADD CONSTRAINT m_handphone_pkey PRIMARY KEY (id);


--
-- Name: m_produk_pkey; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY m_produk
    ADD CONSTRAINT m_produk_pkey PRIMARY KEY (id);


--
-- Name: m_user_password_pkey; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY m_user_password
    ADD CONSTRAINT m_user_password_pkey PRIMARY KEY (id);


--
-- Name: m_user_pkey; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY m_user
    ADD CONSTRAINT m_user_pkey PRIMARY KEY (id);


--
-- Name: t_gcm_outgoing_message_pkey; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY t_gcm_outgoing_message
    ADD CONSTRAINT t_gcm_outgoing_message_pkey PRIMARY KEY (id);


--
-- Name: t_pembayaran_pkey; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY t_pembayaran
    ADD CONSTRAINT t_pembayaran_pkey PRIMARY KEY (id);


--
-- Name: t_tagihan_pkey; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY t_tagihan
    ADD CONSTRAINT t_tagihan_pkey PRIMARY KEY (id);


--
-- Name: uk_9ev77sfyb2omfjx1ugrdcgftm; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY m_produk
    ADD CONSTRAINT uk_9ev77sfyb2omfjx1ugrdcgftm UNIQUE (kode);


--
-- Name: uk_brmb54ld0gqstogmb812xw951; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY m_user
    ADD CONSTRAINT uk_brmb54ld0gqstogmb812xw951 UNIQUE (username);


--
-- Name: uk_rycw44p7cruupkosx3ibmj9q3; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY m_user
    ADD CONSTRAINT uk_rycw44p7cruupkosx3ibmj9q3 UNIQUE (email);


--
-- Name: fk_1gho76p3grval4so0p5kyebib; Type: FK CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY t_pembayaran
    ADD CONSTRAINT fk_1gho76p3grval4so0p5kyebib FOREIGN KEY (id_tagihan) REFERENCES t_tagihan(id);


--
-- Name: fk_gq8pvjcos5d8wk29m8jn3425j; Type: FK CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY t_tagihan
    ADD CONSTRAINT fk_gq8pvjcos5d8wk29m8jn3425j FOREIGN KEY (id_produk) REFERENCES m_produk(id);


--
-- Name: fk_m2a93fmrjgvvkowobo3lixd39; Type: FK CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY m_user_password
    ADD CONSTRAINT fk_m2a93fmrjgvvkowobo3lixd39 FOREIGN KEY (id_user) REFERENCES m_user(id);


--
-- Name: fk_n234f6d67sgkffkn79ck01mh3; Type: FK CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY m_handphone
    ADD CONSTRAINT fk_n234f6d67sgkffkn79ck01mh3 FOREIGN KEY (id_user) REFERENCES m_user(id);


--
-- Name: fk_qssbigmpix7dl6fe9c2tbtjlh; Type: FK CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY t_tagihan
    ADD CONSTRAINT fk_qssbigmpix7dl6fe9c2tbtjlh FOREIGN KEY (id_user) REFERENCES m_user(id);


--
-- PostgreSQL database dump complete
--


package com.sayur.tetangga.cart;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pesanan {
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private List<Data> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data{
        @SerializedName("id")
        private String id;
        @SerializedName("kode_transaksi")
        private String kodeTransaksi;
        @SerializedName("id_users")
        private String idUsers;
        @SerializedName("data_pengiriman")
        private DataPengiriman dataPengiriman;
        @SerializedName("bukti_tf")
        private String buktiTf;
        @SerializedName("status")
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKodeTransaksi() {
            return kodeTransaksi;
        }

        public void setKodeTransaksi(String kodeTransaksi) {
            this.kodeTransaksi = kodeTransaksi;
        }

        public String getIdUsers() {
            return idUsers;
        }

        public void setIdUsers(String idUsers) {
            this.idUsers = idUsers;
        }

        public DataPengiriman getDataPengiriman() {
            return dataPengiriman;
        }

        public void setDataPengiriman(DataPengiriman dataPengiriman) {
            this.dataPengiriman = dataPengiriman;
        }

        public String getBuktiTf() {
            return buktiTf;
        }

        public void setBuktiTf(String buktiTf) {
            this.buktiTf = buktiTf;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public class DataPengiriman{
            @SerializedName("ongkir")
            private String ongkir;
            @SerializedName("penerima")
            private String penerima;
            @SerializedName("waktu")
            private String waktu;
            @SerializedName("catatan")
            private String catatan;
            @SerializedName("payment")
            private String payment;
            @SerializedName("no_telp")
            private String noTelp;
            @SerializedName("alamat")
            private String alamat;

            public String getOngkir() {
                return ongkir;
            }

            public void setOngkir(String ongkir) {
                this.ongkir = ongkir;
            }

            public String getPenerima() {
                return penerima;
            }

            public void setPenerima(String penerima) {
                this.penerima = penerima;
            }

            public String getWaktu() {
                return waktu;
            }

            public void setWaktu(String waktu) {
                this.waktu = waktu;
            }

            public String getCatatan() {
                return catatan;
            }

            public void setCatatan(String catatan) {
                this.catatan = catatan;
            }

            public String getPayment() {
                return payment;
            }

            public void setPayment(String payment) {
                this.payment = payment;
            }

            public String getNoTelp() {
                return noTelp;
            }

            public void setNoTelp(String noTelp) {
                this.noTelp = noTelp;
            }

            public String getAlamat() {
                return alamat;
            }

            public void setAlamat(String alamat) {
                this.alamat = alamat;
            }
        }
    }
}

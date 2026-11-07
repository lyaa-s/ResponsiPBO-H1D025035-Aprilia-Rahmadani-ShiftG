public abstract class perangkatIOT {
    public static final String PROTOKOL_DEFAULT = "MQTT";
    public static final int BATERAI_MAKSIMAL = 100;

    private final String idPerangkat;

    private String nama;
    private String lokasi;
    private int levelBaterai;
    private boolean aktif;

    public perangkatIOT(String idPerangkat, String nama) {
        this(idPerangkat, nama, "Belum diatur", BATERAI_MAKSIMAL);
    }

    public perangkatIOT(String idPerangkat, String nama, String lokasi, int levelBaterai) {
        this.idPerangkat = idPerangkat;
        this.nama = nama;
        this.lokasi = lokasi;
        setLevelBaterai(levelBaterai);
        this.aktif = false;
    }

    public String getIdPerangkat() {
        return idPerangkat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public int getLevelBaterai() {
        return levelBaterai;
    }

    public void setLevelBaterai(int levelBaterai) {
        if (levelBaterai < 0) {
            this.levelBaterai = 0;
        } else if (levelBaterai > BATERAI_MAKSIMAL) {
            this.levelBaterai = BATERAI_MAKSIMAL;
        } else {
            this.levelBaterai = levelBaterai;
        }
    }

    public boolean isAktif() {
        return aktif;
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }

    public void kurangiBaterai(int jumlah) {
        setLevelBaterai(this.levelBaterai - jumlah);
    }

    public void tampilkanInfo() {
        System.out.println("ID       : " +idPerangkat);
        System.out.println("Nama     : " +nama);
        System.out.println("Lokasi   : " + lokasi);
        System.out.println("Baterai  : " +levelBaterai+ "%");
        System.out.println("Status   : " +getStatusTeks());
        System.out.println("Protokol : " +PROTOKOL_DEFAULT);
    }

    public String getStatusTeks() {
        return aktif ? "Aktif" : "NonAktif";
    }

    public int hitungEstimasiJam(int konsumsiPerJam) {
        if (konsumsiPerJam <= 0) {
            return 0;
        }
        return levelBaterai / konsumsiPerJam;
    }

    public abstract void prosesPerintah(String perintah);
}

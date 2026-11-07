public class lampuPintar extends perangkatIOT implements controlLable, connecTable {

    private int kecerahan;
    private boolean terhubung;
    private String ssid;

    public lampuPintar(String id, String nama) {
        super(id, nama);
        this.kecerahan = 0;
        this.terhubung = false;
        this.ssid = "-";
    }

    public lampuPintar(String id, String nama, String lokasi, int baterai) {
        super(id, nama, lokasi, baterai);
        this.kecerahan = 0;
        this.terhubung = false;
        this.ssid = "-";
    }

    public int getKecerahan() {
        return kecerahan;
    }

    public void setKecerahan(int kecerahan) {
        this.kecerahan = Math.max(0, Math.min(100, kecerahan));
    }

    @Override
    public void nyalakan() {
        if (getLevelBaterai() < 5) {
            System.out.println("[" +getNama()+ "] Baterai terlalu rendah, tidak bisa menyala.");
        } else {
            setAktif(true);
            if (kecerahan == 0) {
                kecerahan = 50;
            }
            System.out.println("[" +getNama()+ "] Lampu menyala.");
        }
    }

    @Override
    public void matikan() {
        setAktif(false);
        kecerahan = 0;
        System.out.println("[" + getNama() + "] Lampu dimatikan.");
    }

    @Override
    public boolean isMenyala() {
        return isAktif();
    }

    @Override
    public boolean hubungkan(String ssid) {
        if (ssid == null || ssid.isEmpty()) {
            System.out.println("[" +getNama()+ "] SSID tidak valid.");
            return false;
        }
        this.ssid = ssid;
        this.terhubung = true;
        System.out.println("[" +getNama()+ "] Terhubung ke WiFi " + ssid);
        return true;
    }

    @Override
    public void putuskan() {
        this.terhubung = false;
        this.ssid = "-";
        System.out.println("[" +getNama()+ "] Koneksi diputus.");
    }

    @Override
    public boolean isTerhubung() {
        return terhubung;
    }

    @Override
    public void prosesPerintah(String perintah) {
        if (!isAktif()) {
            System.out.println("[" +getNama()+ "] Perintah '" +perintah+ "' ditolak: lampu sedang mati.");
        } else if (perintah.equals("Terang")) {
            if (kecerahan >= 100) {
                System.out.println("[" +getNama()+ "] Kecerahan sudah maksimal.");
            } else {
                setKecerahan(kecerahan + 25);
                kurangiBaterai(2);
                System.out.println("[" +getNama()+ "] Kecerahan naik menjadi " + kecerahan + "%");
            }
        } else if (perintah.equals("REDUP")) {
            if (kecerahan <= 25) {
                System.out.println("[" +getNama()+ "] Kecerahan sudah minimal.");
            } else {
                setKecerahan(kecerahan - 25);
                System.out.println("[" +getNama()+ "] Kecerahan turun menjadi " + kecerahan + "%");
            }
        } else {
            System.out.println("[" +getNama()+ "] Perintah tidak dikenal: " + perintah);
        }
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Tipe  : Lampu Pintar");
        System.out.println("Cerah : " + kecerahan + "%");
        System.out.println("WiFi  : " + (terhubung ? ssid : "tidak terhubung"));
    }
}
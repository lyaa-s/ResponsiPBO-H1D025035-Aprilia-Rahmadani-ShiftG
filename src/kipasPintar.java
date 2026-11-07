public class kipasPintar extends perangkatIOT implements controlLable {

    public static final int KECEPATAN_MAKSIMAL = 3;

    private int kecepatan;

    public kipasPintar(String id, String nama) {
        super(id, nama);
        this.kecepatan = 0;
    }

    public kipasPintar(String id, String nama, String lokasi, int baterai) {
        super(id, nama, lokasi, baterai);
        this.kecepatan = 0;
    }

    public int getKecepatan() {
        return kecepatan;
    }

    public void setKecepatan(int kecepatan) {
        if (kecepatan < 0) {
            this.kecepatan = 0;
        } else if (kecepatan > KECEPATAN_MAKSIMAL) {
            this.kecepatan = KECEPATAN_MAKSIMAL;
        } else {
            this.kecepatan = kecepatan;
        }
    }

    @Override
    public void nyalakan() {
        setAktif(true);
        kecepatan = 1;
        System.out.println("[" + getNama() + "] Kipas berputar kecepatan 1.");
    }

    @Override
    public void matikan() {
        setAktif(false);
        kecepatan = 0;
        System.out.println("[" + getNama() + "] Kipas berhenti.");
    }

    @Override
    public boolean isMenyala() {
        return isAktif();
    }

    @Override
    public void prosesPerintah(String perintah) {
        if (!isAktif()) {
            System.out.println("[" + getNama() + "] Kipas mati, perintah '" + perintah + "' ditolak.");
        } else if (perintah.equals("KENCANG")) {
            if (kecepatan < KECEPATAN_MAKSIMAL) {
                kecepatan++;
                kurangiBaterai(5);
                System.out.println("[" + getNama() + "] Kecepatan naik ke level " + kecepatan);
            } else {
                System.out.println("[" + getNama() + "] Sudah kecepatan maksimal.");
            }
        } else if (perintah.equals("PELAN")) {
            if (kecepatan > 1) {
                kecepatan--;
                System.out.println("[" + getNama() + "] Kecepatan turun ke level " + kecepatan);
            } else {
                System.out.println("[" + getNama() + "] Sudah kecepatan minimal.");
            }
        } else {
            System.out.println("[" + getNama() + "] Perintah tidak dikenal: " + perintah);
        }
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Tipe     : Kipas Pintar");
        System.out.println("Kecepatan: level " + kecepatan);
    }
}

public class sensorSuhu extends perangkatIOT implements controlLable {

    public static final double AMBANG_PANAS = 35.0;

    private double suhu;
    private int jumlahPeringatan;

    public sensorSuhu(String id, String nama) {
        super(id, nama);
        this.suhu = 25.0;
        this.jumlahPeringatan = 0;
    }

    public sensorSuhu(String id, String nama, String lokasi, int baterai, double suhuAwal) {
        super(id, nama, lokasi, baterai);
        this.suhu = suhuAwal;
        this.jumlahPeringatan = 0;
    }

    public double getSuhu() {
        return suhu;
    }

    public void setSuhu(double suhu) {
        this.suhu = suhu;
    }

    public int getJumlahPeringatan() {
        return jumlahPeringatan;
    }

    public void setJumlahPeringatan(int jumlahPeringatan) {
        this.jumlahPeringatan = jumlahPeringatan;
    }

    @Override
    public void nyalakan() {
        setAktif(true);
        System.out.println("[" +getNama()+ "] Sensor aktif.");
    }

    @Override
    public void matikan() {
        setAktif(false);
        System.out.println("[" +getNama()+ "] Sensor nonaktif.");
    }

    @Override
    public boolean isMenyala() {
        return isAktif();
    }

    @Override
    public void prosesPerintah(String perintah) {
        if (!isAktif()) {
            System.out.println("[" +getNama()+ "] Sensor nonaktif, perintah '" + perintah + "' diabaikan.");
        } else if (perintah.equals("BACA")) {
            suhu += 5.0;
            kurangiBaterai(3);
            System.out.println("[" +getNama()+ "] Suhu terbaca: " + suhu + " C");
            if (suhu > AMBANG_PANAS) {
                jumlahPeringatan++;
                System.out.println("[" +getNama()+ "] PERINGATAN: suhu melewati ambang " + AMBANG_PANAS + " C!");
            }
        } else if (perintah.equals("RESET")) {
            suhu = 25.0;
            jumlahPeringatan = 0;
            System.out.println("[" +getNama()+ "] Sensor di-reset ke 25.0 C");
        } else {
            System.out.println("[" +getNama()+ "] Perintah tidak dikenal: " + perintah);
        }
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Tipe      : Sensor Suhu");
        System.out.println("Suhu      : " + suhu + "C");
        System.out.println("Peringatan: " + jumlahPeringatan + "kali");
    }
}

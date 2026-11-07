public class main {
    public static void main(String[] args) {

        garis("State & Behavior");
        lampuPintar lampu = new lampuPintar("LMP-01", "Lampu Ruang Tamu", "Ruang Tamu", 80);
        lampu.nyalakan();
        String status = lampu.getStatusTeks();
        int estimasi = lampu.hitungEstimasiJam(4);
        System.out.println("Status lampu : " +status);
        System.out.println("Estimasi baterai : " +estimasi+ "jam");

        garis("Tambahan A. Objek Independen & Identitas unik");
        lampuPintar lampuKembar = new lampuPintar("LMP-01", "Lampu Ruang Tamu", "Ruang Tamu", 80);
        lampuKembar.setKecerahan(10);
        System.out.println("Kecerahan lampu : " + lampu.getKecerahan() + "% (tidak ikut berubah)");
        System.out.println("Kecerahan lampuKembar : " + lampuKembar.getKecerahan() + "%");
        System.out.println("lampu == lampuKembar ? " + (lampu == lampuKembar) + " (data mirip, tapi identitas beda)");

        garis("Immutability");
        System.out.println("Protokol default (static final): " + perangkatIOT.PROTOKOL_DEFAULT);
        System.out.println("Baterai maksimal (static final): " + perangkatIOT.BATERAI_MAKSIMAL);
        System.out.println("ID lampu (private final) : " + lampu.getIdPerangkat());
        System.out.println("(ID tidak punya setter, jadi tidak bisa diubah)");

        garis("Enkapsulasi & Overloading");
        sensorSuhu sensorA = new sensorSuhu("SNS-01", "Sensor Dapur");
        sensorSuhu sensorB = new sensorSuhu("SNS-02", "Sensor Kamar", "Kamar Tidur", 90, 27.5);
        System.out.println("Sensor A -> lokasi: " +sensorA.getLokasi()+ ", baterai: " + sensorA.getLevelBaterai());
        System.out.println("Sensor B -> lokasi: " +sensorB.getLokasi()+ ", baterai: " + sensorB.getLevelBaterai());
        sensorA.setLokasi("Dapur Utama");
        System.out.println("Setelah setLokasi, sensor A : " + sensorA.getLokasi());

        garis("Inheritance, Super Chaining, Overriding");
        System.out.println("lampuPintar, sensorSuhu, kipasPintar semuanya extends perangkatIoT.");
        System.out.println("tampilkanInfo() di-override dan memanggil super.tampilkanInfo():");
        System.out.println("info lampu");
        lampu.tampilkanInfo();

        garis("Abstract Class");
        kipasPintar kipas = new kipasPintar("KPS-01", "Kipas Kamar", "Kamar Tidur", 60);
        kipas.prosesPerintah("Kencang");
        kipas.nyalakan();
        kipas.prosesPerintah("Kencang");
        kipas.prosesPerintah("Kencang");
        kipas.prosesPerintah("Kencang");
        kipas.prosesPerintah("Pelan");
        System.out.println("Baterai kipas sekarang: " +kipas.getLevelBaterai()+ "%");

        sensorB.nyalakan();
        sensorB.prosesPerintah("Baca");
        sensorB.prosesPerintah("Baca");
        sensorB.prosesPerintah("Reset");

        garis("Tambahan B. Referensi Tipe Abstract Class");
        perangkatIOT p = new kipasPintar("KPS-03", "Kipas Dapur", "Dapur", 40);
        p.setAktif(true);
        p.prosesPerintah("Kencang");
        p = new sensorSuhu("SNS-04", "Sensor Garasi", "Garasi", 70, 30.0);
        p.setAktif(true);
        p.prosesPerintah("BACA");

        garis("Polimorfisme Interface");
        controlLable c1 = new lampuPintar("LMP-02", "Lampu Kamar", "Kamar Tidur", 70);
        controlLable c2 = new kipasPintar("KPS-02", "Kipas Tamu", "Ruang Tamu", 50);
        controlLable c3 = new sensorSuhu("SNS-03", "Sensor Teras", "Teras", 85, 30.0);
        controlLable[] semua = {c1, c2, c3};
        for (controlLable c : semua) {
            c.nyalakan();
            System.out.println("menyala? " + c.isMenyala());
        }
        for (controlLable c : semua) {
            c.matikan();
        }

        garis("Multiple Interface (implements Controllable, Connectable)");
        connecTable koneksi = lampu;
        koneksi.hubungkan("WiFi-Rumah");
        System.out.println("Terhubung? " + koneksi.isTerhubung());
        lampu.prosesPerintah("Terang");
        lampu.tampilkanInfo();
        koneksi.putuskan();
        lampu.matikan();

        garis("Selesai");
    }

    private static void garis(String judul) {
        System.out.println();
        System.out.println(" " +judul+ " ");
    }
}

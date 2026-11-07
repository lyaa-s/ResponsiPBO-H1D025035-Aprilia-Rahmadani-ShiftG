Nama	   : Aprilia Rahmadani
NIM	   : H1D025035
Shift KRS  : C
Shift Baru : G

Responsi Praktikum PBO (IOT) - Sistem Smart Home

mensimulasikan perangkat IoT pada rumah pintar: lampu, sensor suhu, dan kipas. Semua perangkat mewarisi abstract class perangkatIOT dan dikendalikan lewat interface Controllable (nyala/mati) dan Connectable (koneksi WiFi).

Struktur Class
File	Peran
perangkatIOT.java : abstract class (superclass)
controlLable.java : Interface nyala/mati
connecTable.java : Interface koneksi WiFi
lampuPintar.java : Subclass, implements controllable dan connectable
sensorSuhu.java : Subclass, implements Controllable
kipasPintar.java : Subclass, implements Controllable
main.java : Class pengujian 10 persyaratan
Pemetaan 10 Persyaratan

No	Konsep	Lokasi
1. State dan Behavior		: Atribut dan method void/return di PerangkatIoT
2. Immutability			: PROTOKOL_DEFAULT, BATERAI_MAKSIMAL (public static final), idPerangkat (private final)
3. Enkapsulasi dan Overloading	: Atribut private + getter/setter, this, dua konstruktor di setiap class
4. Inheritance			: lampuPintar, sensorSuhu, kipasPintar extends perangkatIOT; Main terpisah
5. Super Chaining 		: super(...) di konstruktor subclass, super.tampilkanInfo()
6. Method Overriding		: @Override pada tampilkanInfo() dan method lain
7. Abstract Class		: perangkatIOT abstract, punya prosesPerintah() abstract
8. Logika State			: prosesPerintah() di tiap subclass memakai if/else dan mengubah state
9. Polimorfisme Interface	: controlLable c = new lampuPintar(...) di Main
10. Multiple Interface		: lampuPintar implements controlLable, connecTable

Cara Compile dan Run (Terminal)

1. Masuk ke folder project
cd C:\Users\HP\IdeaProjects\Responsi_PraktikumPBO

2. Compile semua file
javac *.java

# 3. Jalankan program
java Main

(Output Program ada di folder domentasi)

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.irrevocablelc;

/**
 *
 * @author m20n9
 */
import com.dimata.dslik.entity.irrevocablelc.*;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import com.dimata.util.Formater;
import javax.servlet.http.HttpServletRequest;

public class FrmIrrevocableLc extends FRMHandler implements I_FRMInterface, I_FRMType {

    private IrrevocableLc entIrrevocableLc;
    public static final String FRM_NAME_IRREVOCABLE_LC = "FRM_NAME_IRREVOCABLE_LC";
    public static final int FRM_FIELD_IRREVOCABLE_LC = 0;
    public static final int FRM_FIELD_FLAG_DETAIL = 1;
    public static final int FRM_FIELD_NO_REKENING = 2;
    public static final int FRM_FIELD_CIF = 3;
    public static final int FRM_FIELD_KODE_JENIS = 4;
    public static final int FRM_FIELD_KODE_TUJUAN = 5;
    public static final int FRM_FIELD_TGL_KELUAR = 6;
    public static final int FRM_FIELD_TGL_JATUH_TEMPO = 7;
    public static final int FRM_FIELD_NO_AKAD_AWAL = 8;
    public static final int FRM_FIELD_TGL_AKAD_AWAL = 9;
    public static final int FRM_FIELD_NO_AKAD_AKHR = 10;
    public static final int FRM_FIELD_TGL_AKAD_AKHIR = 11;
    public static final int FRM_FIELD_BANK_COUNTERPARTY = 12;
    public static final int FRM_FIELD_KODE_VALUTA = 13;
    public static final int FRM_FIELD_PLAFON = 14;
    public static final int FRM_FIELD_NOMINAL = 15;
    public static final int FRM_FIELD_SETORAN_JAMINAN = 16;
    public static final int FRM_FIELD_KODE_KOLEKTIBILITAS = 17;
    public static final int FRM_FIELD_TGL_WAN_PRESTASI = 18;
    public static final int FRM_FIELD_KODE_KONDISI = 19;
    public static final int FRM_FIELD_TGL_KONDISI = 20;
    public static final int FRM_FIELD_KETERANGAN = 21;
    public static final int FRM_FIELD_KODE_KANTOR_CABANG = 22;
    public static final int FRM_FIELD_OPERASI_DATA = 23;
    public static final int FRM_FIELD_OPEN_DATE = 24;
    public static final int FRM_FIELD_STATUS_DATA = 25;
    public static final int FRM_FIELD_PERIODE_ID = 26;
    public static final int FRM_FIELD_STATUS_PERUBAHAN_DATA = 27;
    public static String[] fieldNames = {
        "FRM_FIELD_IRREVOCABLE_LC",
        "FRM_FIELD_FLAG_DETAIL",
        "FRM_FIELD_NO_REKENING",
        "FRM_FIELD_CIF",
        "FRM_FIELD_KODE_JENIS",
        "FRM_FIELD_KODE_TUJUAN",
        "FRM_FIELD_TGL_KELUAR",
        "FRM_FIELD_TGL_JATUH_TEMPO",
        "FRM_FIELD_NO_AKAD_AWAL",
        "FRM_FIELD_TGL_AKAD_AWAL",
        "FRM_FIELD_NO_AKAD_AKHR",
        "FRM_FIELD_TGL_AKAD_AKHIR",
        "FRM_FIELD_BANK_COUNTERPARTY",
        "FRM_FIELD_KODE_VALUTA",
        "FRM_FIELD_PLAFON",
        "FRM_FIELD_NOMINAL",
        "FRM_FIELD_SETORAN_JAMINAN",
        "FRM_FIELD_KODE_KOLEKTIBILITAS",
        "FRM_FIELD_TGL_WAN_PRESTASI",
        "FRM_FIELD_KODE_KONDISI",
        "FRM_FIELD_TGL_KONDISI",
        "FRM_FIELD_KETERANGAN",
        "FRM_FIELD_KODE_KANTOR_CABANG",
        "FRM_FIELD_OPERASI_DATA",
        "FRM_FIELD_OPEN_DATE",
        "FRM_FIELD_STATUS_DATA",
        "FRM_FIELD_PERIODE_ID",
        "FRM_FIELD_STATUS_PERUBAHAN_DATA"
    };
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_INT,
        TYPE_LONG,
        TYPE_INT
    };
    
    public static String[] fieldQuestion = {
        "",
        "",
        "- Kolom ini diisi dengan nomor rekening /Nomor LC."
            + "&#010;- Nomor rekening harus unik (1 (satu) nomor rekening untuk setiap 1 (satu) fasilitas)."
            + "&#010;- Nomor rekening tidak boleh berubah selama fasilitas tersebut tercatat di dalam SLIK."
            + "&#010;- Nomor rekening yang telah digunakan oleh satu fasilitas tidak boleh digunakan untuk fasilitas lainnya (no reuse/no recycle)."
            + "&#010;- Apabila nomor rekening mengandung karakter selain alfabet dan angka, maka karakter tersebut tidak perlu disertakan.",
        "- Kolom ini diisi dengan nomor CIF debitur yang menerima fasilitas."
            + "&#010;- Nomor CIF debitur harus dilaporkan pada segmen data debitur (individu atau badan usaha)."
            + "&#010;- Apabila nomor CIF mengandung karakter selain alfabet dan angka, maka karakter tersebut tidak perlu disertakan.",
        "- Kolom ini diisi dengan 1 (satu) digit Kode Jenis LC sesuai dengan referensi.",
        "- Kolom ini diisi dengan 1 (satu) digit Kode Tujuan L/C sesuai dengan referensi.",
        "- Kolom ini berisi tanggal diterbitkannya/dikeluarkannya LC sesuai dengan yang tercantum pada warkat/dokumen LC.",
        "- Kolom ini berisi tanggal jatuh tempo LC.",
        "- Kolom ini diisi dengan nomor akad awal (akad pertama) fasilitas LC.",
        "- Kolom ini diisi dengan tanggal akad awal (akad pertama) fasilitas LC.",
        "- Kolom ini diisi dengan nomor akad akhir (akad terbaru) fasilitas LC."
            + "&#010;- Apabila tidak ada perubahan/addendum terhadap perjanjian sejak perjanjian/akad pertama kali maka kolom ini diisi sama dengan kolom nomor akad awal.",
        "- Kolom ini diisi dengan tanggal akad akhir (akad terbaru) fasilitas LC sebagaimana tercatat dalam sistem internal pelapor."
            + "&#010;- Apabila tidak ada perubahan/addendum terhadap perjanjian sejak perjanjian/akad pertama kali maka kolom ini diisi sama dengan kolom tanggal akad awal.",
        "- Kolom ini diisi dengan nama bank beneficiary (bank yang disebut dalam warkat LC sebagai pihak yang menerima pembayaran).",
        "- Kolom ini diisi dengan 4 (empat) digit kode valuta fasilitas LC sebagaimana tercantum pada Referensi Kode Valuta.",
        "- Kolom ini diisi dengan plafon fasilitas LC dalam satuan penuh mata uang Rupiah.",
        "- Kolom ini diisi dengan nominal fasilitas LC dalam satuan penuh mata uang Rupiah.",
        "- Kolom ini berisi nilai nominal uang yang diterima pelapor sebagai jaminan yang akan diperhitungkan pada waktu penyelesaian transaksi dalam satuan penuh mata uang Rupiah.",
        "- Kolom ini diisi dengan 1 (satu) digit Kode Kolektibilitas sesuai dengan referensi."
            + "&#010;- Aturan penentuan kolektibilitas mengacu kepada ketentuan pengenai kualitas aktiva produktif yang berlaku pada masing-masing jenis LJK pelapor.",
        "- Kolom ini diisi dengan tanggal wanprestasi apabila terjadi wanprestasi terhadap fasilitas LC yang dilaporkan."
            + "&#010;- Kolom ini wajib diisi apabila terjadi wanprestasi. Sebaliknya kolom ini wajib dikosongkan jika tidak terjadi wan prestasi.",
        "- Kolom ini diisi dengan 2 (dua) digit Kode Kondisi sesuai dengan referensi.",
        "- Kolom ini diisi dengan tanggal pada saat fasilitas LC dinyatakan dalam kondisi sebagaimana dilaporkan dalam kolom Kode Kondisi."
            + "&#010;- Kolom ini wajib diisi apabila kolom Kode Kondisi berisi selain 00. Sebaliknya, kolom ini wajib dikosongkan apabila kolom Kode Kondisi berisi 00.",
        "- Kolom ini diisi apabila ada keterangan tambahan/keterangan lainnya yang diperlukan terkait fasilitas LC yang dilaporkan.",
        "- Kolom ini diisi dengan Kode Kantor Cabang dimana fasilitas L/C dicatat/diregister.",
        "- Kolom ini dengan 1 (satu) digit kode operasi data sesuai dengan referensi.",
        "",
        "",
        "",
        ""
    };
    
    public static String[] fieldError = {
        "",
        "",
        "Pilih nomor rekening",
        "Kolom ini harus diisi",
        "Pilih salah satu kode jenis LC",
        "Pilih salah satu kode tujuan LC",
        "Kolom ini harus diisi",
        "Kolom ini harus diisi",
        "Kolom ini harus diisi",
        "Kolom ini harus diisi",
        "Kolom ini harus diisi",
        "Kolom ini harus diisi",
        "Kolom ini harus diisi",
        "Pilih salah satu kode valuta",
        "Kolom ini harus diisi",
        "Kolom ini harus diisi",
        "Kolom ini harus diisi",
        "Pilih salah satu kode kolektibilitas",
        "Kolom ini harus diisi",
        "Pilih salah satu kode kondisi",
        "Kolom ini harus diisi",
        "Kolom ini harus diisi",
        "Kolom ini harus diisi",
        "Pilih salah satu kode operasi data",
        "",
        "",
        "",
        ""
    };

    public FrmIrrevocableLc() {
    }

    public FrmIrrevocableLc(IrrevocableLc entIrrevocableLc) {
        this.entIrrevocableLc = entIrrevocableLc;
    }

    public FrmIrrevocableLc(HttpServletRequest request, IrrevocableLc entIrrevocableLc) {
        super(new FrmIrrevocableLc(entIrrevocableLc), request);
        this.entIrrevocableLc = entIrrevocableLc;
    }

    public String getFormName() {
        return FRM_NAME_IRREVOCABLE_LC;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int getFieldSize() {
        return fieldNames.length;
    }

    public IrrevocableLc getEntityObject() {
        return entIrrevocableLc;
    }

    public void requestEntityObject(IrrevocableLc entIrrevocableLc) {
        try {
            this.requestParam();
            //entIrrevocableLc.setIrrevocableLc(getLong(FRM_FIELD_IRREVOCABLE_LC));
            entIrrevocableLc.setFlagDetail(getString(FRM_FIELD_FLAG_DETAIL));
            entIrrevocableLc.setNoRekening(getString(FRM_FIELD_NO_REKENING));
            entIrrevocableLc.setCif(getString(FRM_FIELD_CIF));
            entIrrevocableLc.setKodeJenis(getString(FRM_FIELD_KODE_JENIS));
            entIrrevocableLc.setKodeTujuan(getString(FRM_FIELD_KODE_TUJUAN));
            String tglKeluar = getString(FRM_FIELD_TGL_KELUAR);
            entIrrevocableLc.setTglKeluar(Formater.formatDate(tglKeluar, "yyyy-MM-dd"));
            String tglJatuhTempo = getString(FRM_FIELD_TGL_JATUH_TEMPO);
            entIrrevocableLc.setTglJatuhTempo(Formater.formatDate(tglJatuhTempo, "yyyy-MM-dd"));
            entIrrevocableLc.setNoAkadAwal(getString(FRM_FIELD_NO_AKAD_AWAL));
            String tglAkadAwal = getString(FRM_FIELD_TGL_AKAD_AWAL);
            entIrrevocableLc.setTglAkadAwal(Formater.formatDate(tglAkadAwal, "yyyy-MM-dd"));
            entIrrevocableLc.setNoAkadAkhr(getString(FRM_FIELD_NO_AKAD_AKHR));
            String tglAkadAkhir = getString(FRM_FIELD_TGL_AKAD_AKHIR);
            entIrrevocableLc.setTglAkadAkhir(Formater.formatDate(tglAkadAkhir, "yyyy-MM-dd"));
            entIrrevocableLc.setBankCounterparty(getString(FRM_FIELD_BANK_COUNTERPARTY));
            entIrrevocableLc.setKodeValuta(getString(FRM_FIELD_KODE_VALUTA));
            entIrrevocableLc.setPlafon(getFloat(FRM_FIELD_PLAFON));
            entIrrevocableLc.setNominal(getFloat(FRM_FIELD_NOMINAL));
            entIrrevocableLc.setSetoranJaminan(getFloat(FRM_FIELD_SETORAN_JAMINAN));
            entIrrevocableLc.setKodeKolektibilitas(getString(FRM_FIELD_KODE_KOLEKTIBILITAS));
            String tglWanPrestasi = getString(FRM_FIELD_TGL_WAN_PRESTASI);
            entIrrevocableLc.setTglWanPrestasi(Formater.formatDate(tglWanPrestasi, "yyyy-MM-dd"));
            entIrrevocableLc.setKodeKondisi(getString(FRM_FIELD_KODE_KONDISI));
            String tglKondisi = getString(FRM_FIELD_TGL_KONDISI);
            entIrrevocableLc.setTglKondisi(Formater.formatDate(tglKondisi, "yyyy-MM-dd"));
            entIrrevocableLc.setKeterangan(getString(FRM_FIELD_KETERANGAN));
            entIrrevocableLc.setKodeKantorCabang(getString(FRM_FIELD_KODE_KANTOR_CABANG));
            entIrrevocableLc.setOperasiData(getString(FRM_FIELD_OPERASI_DATA));
            String openDate = getString(FRM_FIELD_OPEN_DATE);
            entIrrevocableLc.setOpenDate(Formater.formatDate(openDate, "yyyy-MM-dd"));  
            entIrrevocableLc.setStatusData(getInt(FRM_FIELD_STATUS_DATA));
            entIrrevocableLc.setPeriodeId(getLong(FRM_FIELD_PERIODE_ID));
            entIrrevocableLc.setStatusPerubahanData(getInt(FRM_FIELD_STATUS_PERUBAHAN_DATA));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}

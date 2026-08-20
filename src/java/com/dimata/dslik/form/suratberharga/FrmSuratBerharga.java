/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.suratberharga;

/**
 *
 * @author m20n9
 */
import com.dimata.dslik.entity.suratberharga.*;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import com.dimata.util.Formater;
import javax.servlet.http.HttpServletRequest;

public class FrmSuratBerharga extends FRMHandler implements I_FRMInterface, I_FRMType {

    private SuratBerharga entSuratBerharga;
    public static final String FRM_NAME_SURAT_BERHARGA = "FRM_NAME_SURAT_BERHARGA";
    public static final int FRM_FIELD_SURAT_BERHARGA_OID = 0;
    public static final int FRM_FIELD_FLAG_DETAIL = 1;
    public static final int FRM_FIELD_NO_REKENING = 2;
    public static final int FRM_FIELD_CIF = 3;
    public static final int FRM_FIELD_KODE_JENIS = 4;
    public static final int FRM_FIELD_SOVEREIGN_RATE = 5;
    public static final int FRM_FIELD_LISTING = 6;
    public static final int FRM_FIELD_PERINGKAT = 7;
    public static final int FRM_FIELD_KODE_TUJUAN = 8;
    public static final int FRM_FIELD_TGL_DEBIT = 9;
    public static final int FRM_FIELD_TGL_BELI = 10;
    public static final int FRM_FIELD_TGL_JATUH_TEMPO = 11;
    public static final int FRM_FIELD_KODE_VALUTA = 12;
    public static final int FRM_FIELD_NOMINAL = 13;
    public static final int FRM_FIELD_NILAI_UANG_ASAL = 14;
    public static final int FRM_FIELD_NILAI_PASAR = 15;
    public static final int FRM_FIELD_NILAI_PEROLEHAN = 16;
    public static final int FRM_FIELD_SUKU_BUNGA = 17;
    public static final int FRM_FIELD_TUNGGAKAN = 18;
    public static final int FRM_FIELD_JML_HARI_TUNGGAKAN = 19;
    public static final int FRM_FIELD_KODE_KOLEKTIBILITAS = 20;
    public static final int FRM_FIELD_TGL_MACET = 21;
    public static final int FRM_FIELD_KODE_SEBAB_MACET = 22;
    public static final int FRM_FIELD_KODE_KONDISI = 23;
    public static final int FRM_FIELD_TGL_KONDISI = 24;
    public static final int FRM_FIELD_KETERANGAN = 25;
    public static final int FRM_FIELD_KODE_KANTOR_CABANG = 26;
    public static final int FRM_FIELD_OPERASI_DATA = 27;
    public static final int FRM_FIELD_OPEN_DATE = 28;
    public static final int FRM_FIELD_STATUS_DATA = 29;
    public static final int FRM_FIELD_PERIODE_ID = 30;
    public static final int FRM_FIELD_STATUS_PERUBAHAN_DATA = 31;
    public static String[] fieldNames = {
        "FRM_FIELD_SURAT_BERHARGA_OID", //0
        "FRM_FIELD_FLAG_DETAIL", //1
        "FRM_FIELD_NO_REKENING", //2
        "FRM_FIELD_CIF", //3
        "FRM_FIELD_KODE_JENIS", //4
        "FRM_FIELD_SOVEREIGN_RATE", //5
        "FRM_FIELD_LISTING", //6
        "FRM_FIELD_PERINGKAT", //7
        "FRM_FIELD_KODE_TUJUAN", //8
        "FRM_FIELD_TGL_DEBIT", //9
        "FRM_FIELD_TGL_BELI", //10
        "FRM_FIELD_TGL_JATUH_TEMPO", //11
        "FRM_FIELD_KODE_VALUTA", //12
        "FRM_FIELD_NOMINAL", //13
        "FRM_FIELD_NILAI_UANG_ASAL", //14
        "FRM_FIELD_NILAI_PASAR", //15
        "FRM_FIELD_NILAI_PEROLEHAN", //16
        "FRM_FIELD_SUKU_BUNGA", //17
        "FRM_FIELD_TUNGGAKAN", //18
        "FRM_FIELD_JML_HARI_TUNGGAKAN", //19
        "FRM_FIELD_KODE_KOLEKTIBILITAS", //20
        "FRM_FIELD_TGL_MACET", //21
        "FRM_FIELD_KODE_SEBAB_MACET", //2
        "FRM_FIELD_KODE_KONDISI", //23
        "FRM_FIELD_TGL_KONDISI", //24
        "FRM_FIELD_KETERANGAN", //25
        "FRM_FIELD_KODE_KANTOR_CABANG", //26
        "FRM_FIELD_OPERASI_DATA", //27
        "FRM_FIELD_OPEN_DATE", //28
        "FRM_FIELD_STATUS_DATA", //29,
        "FRM_FIELD_PERIODE_ID",
        "FRM_FIELD_STATUS_PERUBAHAN_DATA"
    };
    public static int[] fieldTypes = {
        TYPE_LONG, //0
        TYPE_STRING, //1
        TYPE_STRING, //2
        TYPE_STRING, //3
        TYPE_STRING, //4
        TYPE_INT, //5
        TYPE_STRING, //6
        TYPE_STRING, //7
        TYPE_STRING, //8
        TYPE_STRING, //9
        TYPE_STRING, //10
        TYPE_STRING, //11
        TYPE_STRING, //12
        TYPE_FLOAT, //13
        TYPE_FLOAT, //14
        TYPE_FLOAT, //15
        TYPE_FLOAT, //16
        TYPE_FLOAT, //17
        TYPE_FLOAT, //18
        TYPE_INT, //19
        TYPE_STRING, //20
        TYPE_STRING, //21
        TYPE_STRING, //22
        TYPE_STRING, //23
        TYPE_STRING, //24
        TYPE_STRING, //25
        TYPE_STRING, //26
        TYPE_STRING, //27
        TYPE_STRING, //28
        TYPE_INT, //29
        TYPE_LONG,
        TYPE_INT
    };
    
    public static String[] fieldQuestion = {
        "", //0
        "", //1
        "- Kolom ini diisi dengan nomor rekening fasilitas surat berharga/nomor surat berharga."
            + "&#010;- Nomor rekening harus unik (1 (satu) nomor rekening untuk setiap 1 (satu) fasilitas)."
            + "&#010;- Nomor rekening tidak boleh berubah selama fasilitas tersebut tercatat di dalam SLIK."
            + "&#010;- Nomor rekening yang telah digunakan oleh satu fasilitas tidak boleh digunakan untuk fasilitas lainnya (no reuse/no recycle)."
            + "&#010;- Apabila nomor rekening mengandung karakter selain alfabet dan angka, maka karakter tersebut tidak perlu disertakan.", //2
        "- Kolom ini diisi dengan nomor CIF debitur yang menerima fasilitas."
            + "&#010;- Nomor CIF debitur harus dilaporkan pada segmen data debitur (individu atau badan usaha)."
            + "&#010;- Apabila nomor CIF mengandung karakter selain alfabet dan angka, maka karakter tersebut tidak perlu disertakan.", //3
        "- Kolom ini diisi dengan 3 (tiga) digit Kode Jenis Surat Berharga sesuai dengan referensi.", //4
        "- Kolom ini diisi dengan sovereign rate negara perusahaan/lembaga penerbit surat berharga.", //5
        "- Kolom ini diisi dengan 1 (satu) digit kode status listing sesuai dengan referensi.", //6
        "- Kolom ini diisi dengan peringkat surat berharga yang dilaporkan.", //7
        "- Kolom ini diisi dengan 1 (satu) digit Kode Tujuan Kepemilikan Surat Berharga sesuai dengan referensi.", //8
        "- Kolom ini diisi dengan tanggal penerbitan surat berharga.", //9
        "- Kolom ini diisi dengan tanggal pembelian surat berharga oleh Pelapor.", //10
        "- Kolom ini diisi dengan tanggal jatuh tempo surat berharga.", //11
        "- Kolom ini diisi dengan 4 (empat) digit kode valuta surat berharga sebagaimana tercantum pada Referensi Kode Valuta.", //12
        "- Kolom ini diisi dengan nilai nominal surat berharga dalam satuan penuh mata uang Rupiah.", //13
        "- Kolom ini diisi dengan nilai nominal surat berharga dalam satuan mata uang asal."
            + "&#010;- Kolom ini wajib diisi apabila kode valuta bukan IDR (Rupiah) dan dikosongkan apabila kode valuta adalah IDR (Rupiah).", //14
        "- Kolom ini diisi dengan nilai pasar surat berharga dalam satuan penuh mata uang Rupiah.", //15
        "- Kolom ini diisi dengan nilai perolehan surat berharga dalam satuan penuh mata uang Rupiah.", //16
        "- Kolom ini diisi dengan persentase suku bunga/imbalan fasilitas surat berharga.", //17
        "- Kolom ini diisi dengan nominal tunggakan (pokok dan/atau bunga) dalam satuan penuh mata uang Rupiah.", //18
        "- Kolom ini diisi dengan jumlah hari tunggakan fasilitas surat berharga yang dilaporkan (tunggakan pokok dan/atau bunga)."
            + "&#010;- Apabila tidak ada tunggakan maka kolom ini diisi dengan “0” (nol).", //19
        "- Kolom ini diisi dengan 1 (satu) digit Kode Kolektibilitas sesuai dengan referensi."
            + "&#010;- Aturan penentuan kolektibilitas mengacu kepada ketentuan mengenai kualitas aktiva produktif yang berlaku pada masing-masing jenis LJK pelapor.", //20
        "- Kolom ini diisi dengan tanggal pada saat fasilitas surat berharga dinyatakan macet."
            + "&#010;- Kolom ini wajib diisi apabila fasilitas surat berharga yang dilaporkan dinyatakan macet. Sebaliknya, kolom ini wajib dikosongkan apabila fasilitas surat berharga yang dilaporkan tidak macet.", //21
        "- Kolom ini diisi dengan 2 (dua) digit Kode Sebab Macet sesuai dengan referensi."
            + "&#010;- Kolom ini wajib diisi apabila surat berharga yang dilaporkan dinyatakan macet. Sebaliknya, kolom ini wajib dikosongkan apabila surat berharga yang dilaporkan tidak macet.", //2
        "- Kolom ini diisi dengan 2 (dua) digit Kode Kondisi sesuai dengan referensi.", //23
        "- Kolom ini diisi dengan tanggal pada saat surat berharga dinyatakan dalam kondisi sebagaimana dilaporkan dalam kolom Kode Kondisi."
            + "&#010;- Kolom ini wajib diisi apabila kolom Kode Kondisi berisi selain 00. Sebaliknya, kolom ini wajib dikosongkan apabila kolom Kode Kondisi berisi 00.", //24
        "- Kolom ini diisi apabila ada keterangan tambahan/keterangan lainnya yang diperlukan terkait surat berharga yang dilaporkan.", //25
        "- Kolom ini berisi Kode Kantor Cabang dimana fasilitas surat berharga dicatat/diregister.", //26
        "- Kolom ini dengan 1 (satu) digit kode operasi data sesuai dengan referensi.", //27
        "", //28
        "", //29,
        "",
        ""
    };
    
    public static String[] fieldError = {
        "", //0
        "", //1
        "Pilih nomor rekening", //2
        "Kolom ini harus diisi", //3
        "Pilih salah satu kode jenis surat berharga", //4
        "Kolom ini harus diisi", //5
        "Pilih salah satu kode listing", //6
        "Kolom ini harus diisi", //7
        "Pilih salah satu kode tujuan kepemilikan", //8
        "Kolom ini harus diisi", //9
        "Kolom ini harus diisi", //10
        "Kolom ini harus diisi", //11
        "Pilih salah satu kode valuta", //12
        "Kolom ini harus diisi", //13
        "Kolom ini harus diisi", //14
        "Kolom ini harus diisi", //15
        "Kolom ini harus diisi", //16
        "Kolom ini harus diisi", //17
        "Kolom ini harus diisi", //18
        "Kolom ini harus diisi", //19
        "Pilih salah satu kode kolektibilitas", //20
        "Kolom ini harus diisi", //21
        "Pilih salah satu kode sebab macet", //2
        "Pilih salah satu kode kondisi", //23
        "Kolom ini harus diisi", //24
        "Kolom ini harus diisi", //25
        "Kolom ini harus diisi", //26
        "Pilih salah satu kode operasi data", //27
        "", //28
        "", //29,
        "",
        ""
    };

    public FrmSuratBerharga() {
    }

    public FrmSuratBerharga(SuratBerharga entSuratBerharga) {
        this.entSuratBerharga = entSuratBerharga;
    }

    public FrmSuratBerharga(HttpServletRequest request, SuratBerharga entSuratBerharga) {
        super(new FrmSuratBerharga(entSuratBerharga), request);
        this.entSuratBerharga = entSuratBerharga;
    }

    public String getFormName() {
        return FRM_NAME_SURAT_BERHARGA;
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

    public SuratBerharga getEntityObject() {
        return entSuratBerharga;
    }

    public void requestEntityObject(SuratBerharga entSuratBerharga) {
        try {
            this.requestParam();
            //entSuratBerharga.setSuratBerhargaOid(getLong(FRM_FIELD_SURAT_BERHARGA_OID));
            entSuratBerharga.setFlagDetail(getString(FRM_FIELD_FLAG_DETAIL));
            entSuratBerharga.setNoRekening(getString(FRM_FIELD_NO_REKENING));
            entSuratBerharga.setCif(getString(FRM_FIELD_CIF));
            entSuratBerharga.setKodeJenis(getString(FRM_FIELD_KODE_JENIS));
            entSuratBerharga.setSovereignRate(getInt(FRM_FIELD_SOVEREIGN_RATE));
            entSuratBerharga.setListing(getString(FRM_FIELD_LISTING));
            entSuratBerharga.setPeringkat(getString(FRM_FIELD_PERINGKAT));
            entSuratBerharga.setKodeTujuan(getString(FRM_FIELD_KODE_TUJUAN));
            String tglTerbit = getString(FRM_FIELD_TGL_DEBIT);
            entSuratBerharga.setTglTerbit(Formater.formatDate(tglTerbit, "yyyy-MM-dd"));
            String tglBeli = getString(FRM_FIELD_TGL_BELI);
            entSuratBerharga.setTglBeli(Formater.formatDate(tglBeli, "yyyy-MM-dd"));
            String tglJatuhTempo = getString(FRM_FIELD_TGL_JATUH_TEMPO);
            entSuratBerharga.setTglJatuhTempo(Formater.formatDate(tglJatuhTempo, "yyyy-MM-dd"));
            entSuratBerharga.setKodeValuta(getString(FRM_FIELD_KODE_VALUTA));
            entSuratBerharga.setNominal(getFloat(FRM_FIELD_NOMINAL));
            entSuratBerharga.setNilaiUangAsal(getFloat(FRM_FIELD_NILAI_UANG_ASAL));
            entSuratBerharga.setNilaiPasar(getFloat(FRM_FIELD_NILAI_PASAR));
            entSuratBerharga.setNilaiPerolehan(getFloat(FRM_FIELD_NILAI_PEROLEHAN));
            entSuratBerharga.setSukuBunga(getFloat(FRM_FIELD_SUKU_BUNGA));
            entSuratBerharga.setTunggakan(getFloat(FRM_FIELD_TUNGGAKAN));
            entSuratBerharga.setJmlHariTunggakan(getInt(FRM_FIELD_JML_HARI_TUNGGAKAN));
            entSuratBerharga.setKodeKolektibilitas(getString(FRM_FIELD_KODE_KOLEKTIBILITAS));
            String tglMacet = getString(FRM_FIELD_TGL_MACET);
            entSuratBerharga.setTglMacet(Formater.formatDate(tglMacet, "yyyy-MM-dd"));
            entSuratBerharga.setKodeSebabMacet(getString(FRM_FIELD_KODE_SEBAB_MACET));
            entSuratBerharga.setKodeKondisi(getString(FRM_FIELD_KODE_KONDISI));
            String tglKondisi = getString(FRM_FIELD_TGL_KONDISI);
            entSuratBerharga.setTglKondisi(Formater.formatDate(tglKondisi, "yyyy-MM-dd"));
            entSuratBerharga.setKeterangan(getString(FRM_FIELD_KETERANGAN));
            entSuratBerharga.setKodeKantorCabang(getString(FRM_FIELD_KODE_KANTOR_CABANG));
            entSuratBerharga.setOperasiData(getString(FRM_FIELD_OPERASI_DATA));
            String openDate = getString(FRM_FIELD_OPEN_DATE);
            entSuratBerharga.setOpenDate(Formater.formatDate(openDate, "yyyy-MM-dd"));
            entSuratBerharga.setStatusData(getInt(FRM_FIELD_STATUS_DATA));
            entSuratBerharga.setPeriodeId(getLong(FRM_FIELD_PERIODE_ID));
            entSuratBerharga.setStatusPerubahanData(getInt(FRM_FIELD_STATUS_PERUBAHAN_DATA));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}

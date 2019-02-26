package cn.com.hisistar.shandongbusuitest;

import android.util.Log;

import java.io.File;
import java.util.Arrays;

/**
 * Created by lixinjian on 2018/3/13.
 */

public class FileUtil {

    private static final String TAG = "FileUtil";


    public static final String AUDIO = "audio";
    public static final String VIDEO = "video";
    public static final String IMAGE = "image";
    public static final String UNKNOWN = "unknown";

    String[] audioFileType = {
            "M3U", "CUE", "PLS", "MP2", "MP3",
            "CDDA", "WMA", "AAC", "M4A", "WAV",
            "AIFF", "OGG", "FLAC", "RA", "MKA",
            "EC3", "AC3", "DTS", "MLP", "APE",
            "OGA", "MID", "MIDI", "XMF", "RTTTL",
            "SMF", "IMY", "RTX", "OTA", "AMR",
            "AWB", "AEA", "APC", "AU", "DAUD",
            "OMA", "EAC3", "GSM", "TRUEHD", "TTA",
            "MPC", "MPC8",
    };
    String[] videoFileType = {
            "AVSTS", "MTS", "M2T", "ISO", "M2TS",
            "TRP", "TP", "PS", "AVS", "SWF",
            "OGM", "FLV", "RMVB", "RM", "3GP",
            "M4V", "MP4", "MOV", "MKV", "IFO",
            "DIVX", "TS", "AVI", "DAT", "VOB",
            "MPG", "MPEG", "ASF", "WMV", "3GPP",
            "3G2", "3GPP2", "F4V", "M1V", "M2V",
            "M2P", "ASF", "DV", "IFF", "MJ2",
            "ANM", "H261", "H263", "H264", "YUV",
            "CIF", "QCIF", "RGB", "VC1", "Y4M",
            "WEBM"
    };
    String[] imageFileType = {
            "JPG", "GIF", "BMP", "JPEG", "TIFF",
            "TIF", "PNG", "DNG", "WBMP", "JFIF",
            "JPE",
    };

    public String getFileType(String fileName) {

        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

        for (String fileType : audioFileType) {
            if (suffix.equalsIgnoreCase(fileType)) {
                //Log.d(TAG, "file type is " + AUDIO);
                return AUDIO;
            }
        }
        for (String fileType : videoFileType) {
            if (suffix.equalsIgnoreCase(fileType)) {
                //Log.d(TAG, "file type is " + VIDEO);
                return VIDEO;
            }
        }
        for (String fileType : imageFileType) {
            if (suffix.equalsIgnoreCase(fileType)) {
                //Log.d(TAG, "file type is " + IMAGE);
                return IMAGE;
            }
        }
        //Log.d(TAG, "file type is " + UNKNOWN);
        return UNKNOWN;
    }

    public String[] getFileName(File file) {
        String fileName[] = file.list();
        return fileName;
    }

    public String[] getVideoAndImageProgramList(File file) {
        int sum = 0;
        String[] programListTemp = new String[file.list().length];
        for (String name : file.list()) {
            if (getFileType(name).equals(VIDEO) || getFileType(name).equals(IMAGE)) {
                programListTemp[sum] = file.getPath() + "/" + name;
//                Log.d(TAG, "getVideoAndImageProgramList: " + name);
                sum++;
            }
        }

        //Log.e(TAG, "getVideoAndImageProgramList: " + sum);

        String[] programList = new String[sum];
        for (int i = 0; i < sum; i++) {
            programList[i] = programListTemp[i];
        }
        Arrays.sort(programList);
        return programList;
    }

    public String[] getImageProgramList(File file) {
        int sum = 0;
        String[] programListTemp = new String[file.list().length];
        for (String name : file.list()) {
            if (getFileType(name).equals(IMAGE)) {
                programListTemp[sum] = file.getPath() + "/" + name;
//                Log.d(TAG, "getImageProgramList: " + programListTemp[sum]);
                sum++;
            }
        }

        //Log.e(TAG, "getImageProgramList: " + sum);

        String[] programList = new String[sum];
        for (int i = 0; i < sum; i++) {
            programList[i] = programListTemp[i];
        }
        Arrays.sort(programList);
        for (String name : programList) {
            //Log.e(TAG, "getImageProgramList: " + name);
        }
        return programList;
    }

    public String[] getMusicProgramList(File file) {
        int sum = 0;
        String[] programListTemp = new String[file.list().length];
        for (String name : file.list()) {
            if (getFileType(name).equals(AUDIO)) {
                programListTemp[sum] = file.getPath() + "/" + name;
                sum++;
            }
        }

//        Log.d(TAG, "getMusicProgramList: " + sum);

        String[] programList = new String[sum];
        for (int i = 0; i < sum; i++) {
            programList[i] = programListTemp[i];
        }
        Arrays.sort(programList);
        return programList;
    }


}
package com.niklasarndt.maveninit.util;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niklas on 2020/08/03.
 */
public class SysoutInterceptor extends OutputStream {

    private final List<Byte> data = new ArrayList<>();
    private final PrintStream original;

    public SysoutInterceptor(PrintStream original) {

        this.original = original;
    }

    @Override
    public void write(int b) {
        data.add((byte) b);
    }

    public String getCollected() {
        byte[] bytes = new byte[data.size()];
        for (int i = 0; i < data.size(); i++) {
            bytes[i] = data.get(i);
        }
        return new String(bytes);
    }
}

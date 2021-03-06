package de.bethibande.utils.io;

import java.io.IOException;
import java.io.OutputStream;


public class MultiOutputStream extends OutputStream {

    private final OutputStream[] wrapped;

    public MultiOutputStream(OutputStream... wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void write(int b) throws IOException {
        for(OutputStream wrapped : this.wrapped) {
            wrapped.write(b);
        }
    }

    @Override
    public void write(byte[] b) throws IOException {
        for(OutputStream wrapped : this.wrapped) {
            wrapped.write(b);
        }
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        for(OutputStream wrapped : this.wrapped) {
            wrapped.write(b, off, len);
        }
    }

    @Override
    public void flush() throws IOException {
        for(OutputStream wrapped : this.wrapped) {
            wrapped.flush();
        }
    }

    @Override
    public void close() throws IOException {
        for(OutputStream wrapped : this.wrapped) {
            wrapped.close();
        }
    }
}
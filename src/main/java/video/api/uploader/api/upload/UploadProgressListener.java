package video.api.uploader.api.upload;

public interface UploadProgressListener {
    void onProgress(Long bytesWritten, Long totalBytes, int chunkCount, int chunkNum);
}

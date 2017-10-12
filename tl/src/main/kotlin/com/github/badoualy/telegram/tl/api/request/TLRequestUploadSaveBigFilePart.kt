package com.github.badoualy.telegram.tl.api.request

import com.github.badoualy.telegram.tl.TLObjectUtils.SIZE_CONSTRUCTOR_ID
import com.github.badoualy.telegram.tl.TLObjectUtils.SIZE_INT32
import com.github.badoualy.telegram.tl.TLObjectUtils.SIZE_INT64
import com.github.badoualy.telegram.tl.TLObjectUtils.computeTLBytesSerializedSize
import com.github.badoualy.telegram.tl.core.TLBool
import com.github.badoualy.telegram.tl.core.TLBytes
import com.github.badoualy.telegram.tl.core.TLMethod
import com.github.badoualy.telegram.tl.serialization.TLDeserializer
import com.github.badoualy.telegram.tl.serialization.TLSerializer
import java.io.IOException

/**
 * @author Yannick Badoual yann.badoual@gmail.com
 * @see <a href="http://github.com/badoualy/kotlogram">http://github.com/badoualy/kotlogram</a>
 */
class TLRequestUploadSaveBigFilePart() : TLMethod<TLBool>() {
    var fileId: Long = 0L

    var filePart: Int = 0

    var fileTotalParts: Int = 0

    var bytes: TLBytes = TLBytes.EMPTY

    private val _constructor: String = "upload.saveBigFilePart#de7b673d"

    override val constructorId: Int = CONSTRUCTOR_ID

    constructor(
            fileId: Long,
            filePart: Int,
            fileTotalParts: Int,
            bytes: TLBytes
    ) : this() {
        this.fileId = fileId
        this.filePart = filePart
        this.fileTotalParts = fileTotalParts
        this.bytes = bytes
    }

    @Throws(IOException::class)
    override fun serializeBody(tlSerializer: TLSerializer) = with (tlSerializer)  {
        writeLong(fileId)
        writeInt(filePart)
        writeInt(fileTotalParts)
        writeTLBytes(bytes)
    }

    @Throws(IOException::class)
    override fun deserializeBody(tlDeserializer: TLDeserializer) = with (tlDeserializer)  {
        fileId = readLong()
        filePart = readInt()
        fileTotalParts = readInt()
        bytes = readTLBytes()
    }

    override fun computeSerializedSize(): Int {
        var size = SIZE_CONSTRUCTOR_ID
        size += SIZE_INT64
        size += SIZE_INT32
        size += SIZE_INT32
        size += computeTLBytesSerializedSize(bytes)
        return size
    }

    override fun toString() = _constructor

    override fun equals(other: Any?): Boolean {
        if (other !is TLRequestUploadSaveBigFilePart) return false
        if (other === this) return true

        return fileId == other.fileId
                && filePart == other.filePart
                && fileTotalParts == other.fileTotalParts
                && bytes == other.bytes
    }
    companion object  {
        const val CONSTRUCTOR_ID: Int = 0xde7b673d.toInt()
    }
}
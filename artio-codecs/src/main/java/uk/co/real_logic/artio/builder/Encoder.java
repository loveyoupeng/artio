/*
 * Copyright 2015-2017 Real Logic Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.real_logic.artio.builder;

import uk.co.real_logic.artio.EncodingException;
import uk.co.real_logic.artio.util.MutableAsciiBuffer;

public interface Encoder
{
    int BITS_IN_INT = 32;

    static int length(final long result)
    {
        return (int)result;
    }

    static int offset(final long result)
    {
        return (int)(result >> BITS_IN_INT);
    }

    static long result(final int length, final int offset)
    {
        return length | ((long)offset) << BITS_IN_INT;
    }

    /**
     * Encode the message onto a buffer in FIX tag=value\001 format.
     *
     * @param buffer the buffer to encode the message to.
     * @param offset the offset within the buffer to start encoding the message at.
     * @return the offset and length of the encoded message on the buffer packed into a long
     * @throws EncodingException if a required field (other than the message sequence number)
     *                           is missing and codec validation is enabled.
     */
    long encode(MutableAsciiBuffer buffer, int offset);

    /**
     * Resets the encoder. Sets all the fields back to their uninitialized state.
     */
    void reset();

    long messageType();

    SessionHeaderEncoder header();

    void resetMessage();
}

/*
 * Copyright 2023 The Fury Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.fury.format.row;

import java.math.BigDecimal;

public interface Setters {

  void setNullAt(int ordinal);

  // fixed-width field
  // primitive types to avoid box/unbox
  void setBoolean(int ordinal, boolean value);

  void setByte(int ordinal, byte value);

  void setShort(int ordinal, short value);

  void setInt(int ordinal, int value);

  void setLong(int ordinal, long value);

  void setFloat(int ordinal, float value);

  void setDouble(int ordinal, double value);

  void setDate(int ordinal, int value);

  void setTimestamp(int ordinal, long value);

  default void setDecimal(int ordinal, BigDecimal value) {
    throw new UnsupportedOperationException();
  }

  // variable-length field setter must be called with ordinal strictly increase monotonically
  default void setString(int ordinal, String value) {
    throw new UnsupportedOperationException();
  }

  default void setBinary(int ordinal, byte[] value) {
    throw new UnsupportedOperationException();
  }

  default void setStruct(int ordinal, Row value) {
    throw new UnsupportedOperationException();
  }

  default void setArray(int ordinal, ArrayData value) {
    throw new UnsupportedOperationException();
  }

  default void setMap(int ordinal, MapData value) {
    throw new UnsupportedOperationException();
  }
}

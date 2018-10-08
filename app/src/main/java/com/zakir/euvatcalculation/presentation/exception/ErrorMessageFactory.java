
package com.zakir.euvatcalculation.presentation.exception;

import android.content.Context;

import com.zakir.euvatcalculation.R;
import com.zakir.euvatcalculation.data.exception.EmptyEUVatRateListException;
import com.zakir.euvatcalculation.data.exception.NetworkConnectionException;

public class ErrorMessageFactory {

  private ErrorMessageFactory() {
    //empty
  }

  public static String create(Context context, Throwable throwable) {
    String message = context.getString(R.string.exception_message_generic);

    if (throwable instanceof NetworkConnectionException) {
      message = context.getString(R.string.exception_message_no_connection);
    } else if (throwable instanceof EmptyEUVatRateListException) {
      message = context.getString(R.string.exception_message_no_data_found);
    }

    return message;
  }
}

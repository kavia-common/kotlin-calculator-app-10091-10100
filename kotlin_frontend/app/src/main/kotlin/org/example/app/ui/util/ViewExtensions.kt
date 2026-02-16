package org.example.app.ui.util

import android.view.View

object ViewExtensions {
    // PUBLIC_INTERFACE
    fun View.setVisible(visible: Boolean) {
        /** Convenience extension to show/hide a view. */
        this.visibility = if (visible) View.VISIBLE else View.GONE
    }
}

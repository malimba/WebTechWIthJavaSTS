#!/bin/bash

# Script to fix all misplaced files from com/appsdeveloper/blog to com/appsdeveloperblog

SRC_DIR="src/main/java"
OLD_ROOT="$SRC_DIR/com/appsdeveloper/blog"
NEW_ROOT="$SRC_DIR/com/appsdeveloperblog"

echo "📦 Fixing all misplaced files under: $OLD_ROOT"
echo "➡️ Moving to: $NEW_ROOT"

# Check if the old root exists
if [ ! -d "$OLD_ROOT" ]; then
  echo "✅ No misplaced files found. Everything looks good!"
  exit 0
fi

# Step 1: Move files
find "$OLD_ROOT" -type f -name "*.java" | while read file; do
  # Compute relative path after 'blog'
  REL_PATH="${file#"$OLD_ROOT/"}"

  # Compute new full path
  DEST_PATH="$NEW_ROOT/$REL_PATH"
  DEST_DIR=$(dirname "$DEST_PATH")

  # Create destination directory
  mkdir -p "$DEST_DIR"

  echo "🔁 Moving $file -> $DEST_PATH"
  mv "$file" "$DEST_PATH"

  # Step 2: Fix package declaration inside the file
  echo "🔧 Fixing package declaration in $DEST_PATH"
  sed -i 's|package com\.appsdeveloper\.blog|package com.appsdeveloperblog|g' "$DEST_PATH"

  # Step 3: Fix any incorrect imports in the file
  sed -i 's|import com\.appsdeveloper\.blog|import com.appsdeveloperblog|g' "$DEST_PATH"
done

# Step 4: Remove the now-empty old directory structure
echo "🧹 Cleaning up old directories..."
find "$OLD_ROOT" -type d -empty -delete

echo "✅ All misplaced files have been fixed and moved under com.appsdeveloperblog"


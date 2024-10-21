package tahia.formatter;

import org.eclipse.jface.text.*;

public class SimpleDocument implements IDocument {

    final StringBuilder source;

    public SimpleDocument() {
        this.source = new StringBuilder();
    }

    public SimpleDocument(String source) {
        this.source = new StringBuilder(source);
    }

    @Override
    public char getChar(int offset) throws BadLocationException {
        return source.charAt(offset);
    }

    @Override
    public int getLength() {
        return source.length();
    }

    @Override
    public String get() {
        return source.toString();
    }

    @Override
    public String get(int offset, int length) throws BadLocationException {
        return source.substring(offset, offset + length);
    }

    @Override
    public void set(String text) {
        source.setLength(0);
        source.append(text);
    }

    @Override
    public void replace(int offset, int length, String text) throws BadLocationException {
        source.replace(offset, offset + length, text);
    }

    @Override
    public void addDocumentListener(IDocumentListener listener) {
        throw new UnsupportedOperationException("Unimplemented method 'addDocumentListener'");
    }

    @Override
    public void removeDocumentListener(IDocumentListener listener) {
        throw new UnsupportedOperationException("Unimplemented method 'removeDocumentListener'");
    }

    @Override
    public void addPrenotifiedDocumentListener(IDocumentListener documentAdapter) {
        throw new UnsupportedOperationException("Unimplemented method 'addPrenotifiedDocumentListener'");
    }

    @Override
    public void removePrenotifiedDocumentListener(IDocumentListener documentAdapter) {
        throw new UnsupportedOperationException("Unimplemented method 'removePrenotifiedDocumentListener'");
    }

    @Override
    public void addPositionCategory(String category) {
        throw new UnsupportedOperationException("Unimplemented method 'addPositionCategory'");
    }

    @Override
    public void removePositionCategory(String category) throws BadPositionCategoryException {
        throw new UnsupportedOperationException("Unimplemented method 'removePositionCategory'");
    }

    @Override
    public String[] getPositionCategories() {
        throw new UnsupportedOperationException("Unimplemented method 'getPositionCategories'");
    }

    @Override
    public boolean containsPositionCategory(String category) {
        throw new UnsupportedOperationException("Unimplemented method 'containsPositionCategory'");
    }

    @Override
    public void addPosition(Position position) throws BadLocationException {
        throw new UnsupportedOperationException("Unimplemented method 'addPosition'");
    }

    @Override
    public void removePosition(Position position) {
        throw new UnsupportedOperationException("Unimplemented method 'removePosition'");
    }

    @Override
    public void addPosition(String category, Position position) throws BadLocationException,
        BadPositionCategoryException {
        throw new UnsupportedOperationException("Unimplemented method 'addPosition'");
    }

    @Override
    public void removePosition(String category, Position position) throws BadPositionCategoryException {
        throw new UnsupportedOperationException("Unimplemented method 'removePosition'");
    }

    @Override
    public Position[] getPositions(String category) throws BadPositionCategoryException {
        throw new UnsupportedOperationException("Unimplemented method 'getPositions'");
    }

    @Override
    public boolean containsPosition(String category, int offset, int length) {
        throw new UnsupportedOperationException("Unimplemented method 'containsPosition'");
    }

    @Override
    public int computeIndexInCategory(String category, int offset) throws BadLocationException,
        BadPositionCategoryException {
        throw new UnsupportedOperationException("Unimplemented method 'computeIndexInCategory'");
    }

    @Override
    public void addPositionUpdater(IPositionUpdater updater) {
        throw new UnsupportedOperationException("Unimplemented method 'addPositionUpdater'");
    }

    @Override
    public void removePositionUpdater(IPositionUpdater updater) {
        throw new UnsupportedOperationException("Unimplemented method 'removePositionUpdater'");
    }

    @Override
    public void insertPositionUpdater(IPositionUpdater updater, int index) {
        throw new UnsupportedOperationException("Unimplemented method 'insertPositionUpdater'");
    }

    @Override
    public IPositionUpdater[] getPositionUpdaters() {
        throw new UnsupportedOperationException("Unimplemented method 'getPositionUpdaters'");
    }

    @Override
    public String[] getLegalContentTypes() {
        throw new UnsupportedOperationException("Unimplemented method 'getLegalContentTypes'");
    }

    @Override
    public String getContentType(int offset) throws BadLocationException {
        throw new UnsupportedOperationException("Unimplemented method 'getContentType'");
    }

    @Override
    public ITypedRegion getPartition(int offset) throws BadLocationException {
        throw new UnsupportedOperationException("Unimplemented method 'getPartition'");
    }

    @Override
    public ITypedRegion[] computePartitioning(int offset, int length) throws BadLocationException {
        throw new UnsupportedOperationException("Unimplemented method 'computePartitioning'");
    }

    @Override
    public void addDocumentPartitioningListener(IDocumentPartitioningListener listener) {
        throw new UnsupportedOperationException("Unimplemented method 'addDocumentPartitioningListener'");
    }

    @Override
    public void removeDocumentPartitioningListener(IDocumentPartitioningListener listener) {
        throw new UnsupportedOperationException("Unimplemented method 'removeDocumentPartitioningListener'");
    }

    @Override
    public void setDocumentPartitioner(IDocumentPartitioner partitioner) {
        throw new UnsupportedOperationException("Unimplemented method 'setDocumentPartitioner'");
    }

    @Override
    public IDocumentPartitioner getDocumentPartitioner() {
        throw new UnsupportedOperationException("Unimplemented method 'getDocumentPartitioner'");
    }

    @Override
    public int getLineLength(int line) throws BadLocationException {
        throw new UnsupportedOperationException("Unimplemented method 'getLineLength'");
    }

    @Override
    public int getLineOfOffset(int offset) throws BadLocationException {
        throw new UnsupportedOperationException("Unimplemented method 'getLineOfOffset'");
    }

    @Override
    public int getLineOffset(int line) throws BadLocationException {
        throw new UnsupportedOperationException("Unimplemented method 'getLineOffset'");
    }

    @Override
    public IRegion getLineInformation(int line) throws BadLocationException {
        throw new UnsupportedOperationException("Unimplemented method 'getLineInformation'");
    }

    @Override
    public IRegion getLineInformationOfOffset(int offset) throws BadLocationException {
        throw new UnsupportedOperationException("Unimplemented method 'getLineInformationOfOffset'");
    }

    @Override
    public int getNumberOfLines() {
        throw new UnsupportedOperationException("Unimplemented method 'getNumberOfLines'");
    }

    @Override
    public int getNumberOfLines(int offset, int length) throws BadLocationException {
        throw new UnsupportedOperationException("Unimplemented method 'getNumberOfLines'");
    }

    @Override
    public int computeNumberOfLines(String text) {
        throw new UnsupportedOperationException("Unimplemented method 'computeNumberOfLines'");
    }

    @Override
    public String[] getLegalLineDelimiters() {
        throw new UnsupportedOperationException("Unimplemented method 'getLegalLineDelimiters'");
    }

    @Override
    public String getLineDelimiter(int line) throws BadLocationException {
        throw new UnsupportedOperationException("Unimplemented method 'getLineDelimiter'");
    }

    @Override
    public int search(
        int startOffset,
        String findString,
        boolean forwardSearch,
        boolean caseSensitive,
        boolean wholeWord
    ) throws BadLocationException {
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

}

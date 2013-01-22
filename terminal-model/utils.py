import gzip
import sys

def open_cmdline_file(filename, mode='r'):
    if filename == "-":
        assert ('r' in mode) != ('w' in mode)
        return sys.stdin if 'r' in mode else sys.stdout
    elif filename.endswith('.gz'):
        return gzip.open(filename, mode)
    else:
        return open(filename, mode)

def close_cmdline_file(filehandle):
    if filehandle is not sys.stdin or filehandle is not sys.stdout:
        filehandle.close()
